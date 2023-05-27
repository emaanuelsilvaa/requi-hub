package br.com.imd.requihub.usecase;

import br.com.imd.requihub.entity.Attachment;
import br.com.imd.requihub.entity.Author;
import br.com.imd.requihub.entity.Catalog;
import br.com.imd.requihub.model.*;
import br.com.imd.requihub.repository.*;
import br.com.imd.requihub.usecase.interfaces.ICatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CatalogManagerImpl implements ICatalog {

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    private final CatalogRepresentationTypeRepository catalogRepresentationTypeRepository;

    private final CatalogSubjectTagsRepository catalogSubjectTagsRepository;

    private final CatalogCategoryTypeRepository catalogCategoryTypeRepository;

    private static final String catalogPath = "C:/CatalogFiles";

    @Override
    public Optional<CatalogModel> createNewCatalog(CatalogModel catalogModel) {

        // validate information

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        /* find category type */
        final Optional<CatalogCategoryTypeModel> categoryTypeModel = catalogCategoryTypeRepository.findByType(catalogModel.getCategoryType().getType());
        /* find representation type */
        final Optional<CatalogRepresentationTypeModel> representationTypeModel = catalogRepresentationTypeRepository.findByType(catalogModel.getRepresentationTypeModel().getType());

        /* Initial Attachment*/
        catalogModel.setAttachment(catalogModel.getAttachment());
        /* find tags */
        catalogModel.setCategoryType(categoryTypeModel.get());

        catalogModel.setRepresentationTypeModel(representationTypeModel.get());

        catalogModel.setEnabled(true);

        catalogModel.setVersion("0.0."+1);

        /* config evaluations */

        if(author.isPresent()){
            catalogModel.setAuthor(author.get());
            return Optional.of(catalogRepository.save(catalogModel));
        }
        else
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
    }

    @Override
    public Optional<CatalogModel> updateCatalog(CatalogModel catalogModel) {
        return Optional.of(catalogRepository.save(catalogModel));
    }

    @Transactional
    @Override
    public Optional<CatalogModel> deleteCatalog(Long catalogId) {


        File thumbnailFile = new File(catalogRepository.findById(catalogId).get().getAttachment().getThumbnailLink());

        File file = new File(catalogRepository.findById(catalogId).get().getAttachment().getAttachmentLink());

        if(thumbnailFile.delete() && file.delete()) {
            if (catalogRepository.existsById(catalogId)) {
                catalogRepository.deleteAllById(Collections.singleton(catalogId));

            }
           else{
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY, "Error on delete catalog");
            }
            File pathCatalogID = new File(catalogPath+"/"+ catalogId);
            if(!pathCatalogID.exists()){

                System.out.println("Directory does not exist.");
            }else{
                try{
                    delete(pathCatalogID);
                }catch(IOException e){
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Error on delete catalog");
        }
        return  null;
    }

    @Override
    public Page<CatalogModel> getAllCatalogs(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }


    @Override
    public Page<CatalogModel> getCatalogsByAuthor(Long author) {
        final List<CatalogModel> catalogModels = catalogRepository.findAllByAuthorId(author);

        catalogModels.forEach(catalogModel -> {
            File file = new File((catalogModel.getAttachment().getThumbnailLink()));
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = null;
            try {
                resource = new ByteArrayResource(Files.readAllBytes(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            final String thumbBase64 = new String(Base64.getEncoder().encode(resource.getByteArray()));
            catalogModel.getAttachment().setThumbnailLink(thumbBase64);
        });
        return new PageImpl<>(catalogModels);
    }

    @Override
    public Optional<Catalog> findCatalogById(Long id) {
        final Optional<CatalogModel> catalogModel =  catalogRepository.findById(id);

        try{
            if (catalogModel.isPresent()){

                File file = new File((catalogModel.get().getAttachment().getThumbnailLink()));
                Path path = Paths.get(file.getAbsolutePath());
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
                final String thumbBase64 = new String(Base64.getEncoder().encode(resource.getByteArray()));

                final Attachment attachment = Attachment.builder()
                        .fileSize(catalogModel.get().getAttachment().getFileSize())
                        .fileType(catalogModel.get().getAttachment().getFileType())
                        .thumbnailLink(thumbBase64)
                        .build();
                final Author author = Author.builder()
                        .id(catalogModel.get().getAuthor().getId())
                        .firstName(catalogModel.get().getAuthor().getFirstName())
                        .lastName(catalogModel.get().getAuthor().getLastName())
                        .about(catalogModel.get().getAuthor().getAbout())
                        .email(catalogModel.get().getAuthor().getEmail())
                        .build();

                final Catalog catalog = Catalog.builder()
                        .id(catalogModel.get().getId())
                        .creationTime(catalogModel.get().getCreationTime())
                        .lastEdited(catalogModel.get().getLastEdited())
                        .version(catalogModel.get().getVersion())
                        .title(catalogModel.get().getTitle())
                        .description(catalogModel.get().getDescription())
                        .enabled(catalogModel.get().getEnabled())
                        .attachment(attachment)
                        .author(author)
                        .categoryType(catalogModel.get().getCategoryType())
                        .representationTypeModel(catalogModel.get().getRepresentationTypeModel())
                        .subjectTags(catalogModel.get().getSubjectTags())
                        .build();
                return Optional.of(catalog);
            }
            else{
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY, "Error");
            }
        }catch (Exception exception){

        }
        return Optional.of(Catalog.builder().build());
    }

    public static void delete(File file)
            throws IOException{

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
