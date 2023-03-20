package br.com.imd.requihub.controller;


import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.usecase.CatalogCategoryTypeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/catalog/category")
@RestController
@RequiredArgsConstructor
public class CatalogCategoryTypeController {

    private final CatalogCategoryTypeImpl catalogCategoryType;

    @GetMapping
    public ResponseEntity<Page<CatalogCategoryTypeModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalogCategoryType.getAllCatalogsTypes(pageable));
    }
    @PostMapping("/create")
    public Optional<CatalogCategoryTypeModel> createCatalog(@RequestBody CatalogCategoryTypeModel catalogCategoryTypeModel){

        return this.catalogCategoryType.createNewCatalogType(catalogCategoryTypeModel);
    }

    @PostMapping("/delete")
    public Optional<CatalogCategoryTypeModel> deleteCatalogByAuthor(@RequestBody CatalogCategoryTypeModel catalogCategoryTypeModel){
        this.catalogCategoryType.deleteCatalogType(catalogCategoryTypeModel);
        return Optional.of(catalogCategoryTypeModel);
    }

}
