package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadDownloadImpl {
    private static final String path = "C:/CatalogFiles";

    @Autowired
    private final HttpServletRequest request;

    private final CatalogManagerImpl catalogManager;

    public List<String> uploadFile(MultipartFile file, String id) throws Exception {
        String realPathtoUploads = path + "/id";

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            //String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
            if (!new File(realPathtoUploads).exists()) {
                new File(realPathtoUploads).mkdir();
            }
            //log.info("realPathtoUploads = {}", realPathtoUploads);
            String orgName = file.getOriginalFilename();
            String filePath = realPathtoUploads + orgName;
            File dest = new File(filePath);
            file.transferTo(dest);
            final Optional<CatalogModel> catalogModel = catalogManager.findCatalogById(new Long(id));
            catalogModel.get().getAttachmentModel().setAttachmentLink(dest.getAbsolutePath());
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

}
