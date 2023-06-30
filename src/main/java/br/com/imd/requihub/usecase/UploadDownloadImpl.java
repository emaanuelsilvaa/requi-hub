package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.repository.CatalogRepository;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
@RequiredArgsConstructor
public class UploadDownloadImpl {
    private static final String path = "/CatalogFiles";

    @Autowired
    private final HttpServletRequest request;

    private final CatalogManagerImpl catalogManager;

    private final CatalogRepository catalogRepository;

    public List<String> uploadFile(MultipartFile file, String id) throws Exception {
        String realPathtoUploads = path + "/"+ id+"/file/";
        String realPathToThumbnails = path + "/"+id+"/thumbnail/";

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            //String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
            if (!new File(realPathtoUploads).exists()) {
                new File(realPathtoUploads).mkdirs();
            }
            if (!new File(realPathToThumbnails).exists()) {
                new File(realPathToThumbnails).mkdirs();
            }

            file.getContentType();
            //log.info("realPathtoUploads = {}", realPathtoUploads);
            String orgName = file.getOriginalFilename();
            String filePath = realPathtoUploads + orgName;
            File pdfFile = new File(filePath);
            file.transferTo(pdfFile);

            Optional<CatalogModel> catalogModel = catalogRepository.findById(new Long(id));

            catalogModel.get().getAttachment().setAttachmentLink(pdfFile.getAbsolutePath());

            String destinationThumbnail = createThumbnail(pdfFile, realPathToThumbnails, id, Objects.requireNonNull(file.getContentType()).contains("pdf"));

            catalogModel.get().getAttachment().setThumbnailLink(destinationThumbnail);

            catalogManager.updateCatalog(catalogModel.get());

        }

        List<String> list = new ArrayList<String>();
        File files = new File(path);
        String[] fileList = ((File) files).list();
        for (String name : fileList) {
            list.add(name);
        }
        return list;
    }

    public List<String> getListofFiles() throws Exception {

        List<String> list = new ArrayList<String>();
        File files = new File(path);
        String[] fileList = files.list();
        for (String name : fileList) {
            list.add(name);
        }

        return list;

    }

    public String createThumbnail(final File file, final String destination, final String catalogId, final boolean isPDF) throws IOException {
        try{
            if (!isPDF){
                File jpgDestFile = new File(destination + file.getName()+".jpg");
                FileSystemUtils.copyRecursively(file, jpgDestFile);
                return destination + file.getName()+".jpg";
            }
            String pdfPath = path + "/"+catalogId+"/thumbnail/"+file.getName()+"N_V";
            PdfReader reader = new PdfReader(file.getAbsolutePath());
            PdfStamper pdfStamper = new PdfStamper(reader,new FileOutputStream(pdfPath));
            pdfStamper.getWriter().setPdfVersion(PdfWriter.PDF_VERSION_1_4);
            pdfStamper.close();
            reader.close();

            RandomAccessFile raf = new RandomAccessFile(pdfPath, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdf = new com.sun.pdfview.PDFFile(buf);

            PDFPage page = pdf.getPage(0);

            // create the image
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
                    (int) page.getBBox().getHeight());
            BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height,
                    BufferedImage.TYPE_INT_RGB);

            Image image = page.getImage(rect.width, rect.height,    // width & height
                    rect,                       // clip rect
                    null,                       // null for the ImageObserver
                    true,                       // fill background with white
                    true                        // block until drawing is done
            );
            Graphics2D bufImageGraphics = bufferedImage.createGraphics();
            bufImageGraphics.drawImage(image, 0, 0, null);
            ImageIO.write(bufferedImage, "JPG", new File( destination + file.getName()+".jpg" ));
            return destination + file.getName() + ".jpg";
        }catch (Exception exception){
            if (catalogRepository.existsById(parseLong(catalogId) )) {
                catalogRepository.deleteAllById(Collections.singleton((parseLong(catalogId))));
            }
            delete(new File(path + "/"+ catalogId));
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Error on save catalog");
        }

    }

    public static void delete(File file)
            throws IOException {

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

}
