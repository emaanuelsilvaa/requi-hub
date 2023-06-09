package br.com.imd.requihub.controller;

import br.com.imd.requihub.entity.Catalog;
import br.com.imd.requihub.model.AttachmentModel;
import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.repository.CatalogRepository;
import br.com.imd.requihub.usecase.CatalogCategoryTypeImpl;
import br.com.imd.requihub.usecase.CatalogManagerImpl;
import br.com.imd.requihub.usecase.CatalogRepresentationTypeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/public/catalog")
@RequiredArgsConstructor
public class CatalogPublicController {
    private final CatalogManagerImpl catalog;

    private final CatalogCategoryTypeImpl catalogCategoryType;

    private final CatalogRepresentationTypeImpl catalogRepresentationType;

    private final CatalogRepository catalogRepository;

    @GetMapping("/filter")
    public ResponseEntity<Page<CatalogModel>> getCatalogsByFilter(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "bibliographicReference", required = true) String bibliographicReference,
            @RequestParam(value = "categoryType", required = true) String categoryType,
            @RequestParam(value = "representationType", required = true) String representationType,
            @RequestParam(value = "subjectTags", required = true) List<String> subjectTags,
            Pageable pageable){
        return ResponseEntity.ok(this.catalog.getCatalogsByFilter(userId,title,bibliographicReference,categoryType,representationType, subjectTags,pageable));
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CatalogCategoryTypeModel>> getAllCategories(){
        return ResponseEntity.ok(this.catalogCategoryType.getAllDefaultCatalogsTypes());
    }

    @GetMapping("/representations")
    public ResponseEntity<Page<CatalogRepresentationTypeModel>> getAllRepresentations(){
        return ResponseEntity.ok(this.catalogRepresentationType.getAllDefaultsRepresentationTypes());
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<Catalog>> findCatalogById(@PathVariable Long id){
        return ResponseEntity.ok( catalog.findCatalogById(id) );
    }

    @GetMapping(path = "/download/{catalogId}")
    public ResponseEntity<Resource> download(@PathVariable("catalogId") Long catalogId) throws IOException {
        final AttachmentModel attachmentModel = catalogRepository.findById(catalogId).get().getAttachment();
        File file = new File(attachmentModel.getAttachmentLink());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().headers(this.headers(file.getName())).contentLength(file.length())
                .contentType(MediaType.parseMediaType(attachmentModel.getFileType())).body(resource);
    }

    private HttpHeaders headers(String name) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }

}
