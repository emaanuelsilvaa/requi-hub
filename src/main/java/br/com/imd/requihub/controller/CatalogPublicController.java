package br.com.imd.requihub.controller;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.usecase.CatalogCategoryTypeImpl;
import br.com.imd.requihub.usecase.CatalogManagerImpl;
import br.com.imd.requihub.usecase.CatalogRepresentationTypeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/catalog")
@RequiredArgsConstructor
public class CatalogPublicController {
    private final CatalogManagerImpl catalog;

    private final CatalogCategoryTypeImpl catalogCategoryType;

    private final CatalogRepresentationTypeImpl catalogRepresentationType;

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

}
