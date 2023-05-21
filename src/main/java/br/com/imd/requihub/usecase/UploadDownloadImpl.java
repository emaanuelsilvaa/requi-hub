package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogModel;
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
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadDownloadImpl {
    private static final String path = "C:/CatalogFiles";

    @Autowired
    private final HttpServletRequest request;

    private final CatalogManagerImpl catalogManager;

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
            Optional<CatalogModel> catalogModel = catalogManager.findCatalogById(new Long(id));

            catalogModel.get().getAttachment().setAttachmentLink(pdfFile.getAbsolutePath());

            String destinationThumbnail = createThumbnail(pdfFile, realPathToThumbnails, Objects.requireNonNull(file.getContentType()).contains("pdf"));

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

    public String createThumbnail(final File file, final String destination,final boolean isPDF){
        try{
            if (!isPDF){
                File jpgDestFile = new File(destination + file.getName()+".jpg");
                FileSystemUtils.copyRecursively(file, jpgDestFile);
                return destination + file.getName()+".jpg";
            }
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdf = new PDFFile(buf);
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
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Error");
        }

    }

}
