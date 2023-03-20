package br.com.imd.requihub.controller;

import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.CatalogTagsSubjectModel;
import br.com.imd.requihub.usecase.CatalogSubjectTagsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/catalog/tags")
@RestController
@RequiredArgsConstructor
public class CatalogSubjectTagsController {

    private final CatalogSubjectTagsImpl catalogSubjectTags;

    @GetMapping
    public ResponseEntity<Page<CatalogTagsSubjectModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalogSubjectTags.getAllCatalogsSubjects(pageable));
    }
    @PostMapping("/create")
    public List<CatalogTagsSubjectModel> createCatalog(@RequestBody List<CatalogTagsSubjectModel> catalogTagsSubjectModel){
        return this.catalogSubjectTags.createNewCatalogTagsSubjects(catalogTagsSubjectModel);
    }

    @PostMapping("/delete")
    public List<CatalogTagsSubjectModel> deleteCatalogByAuthor(@RequestBody List<CatalogTagsSubjectModel> catalogTagsSubjectModel){
        this.catalogSubjectTags.deleteCatalogTagsSubjects(catalogTagsSubjectModel);
        return catalogTagsSubjectModel;
    }
}
