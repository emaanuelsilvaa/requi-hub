package br.com.imd.requihub.controller;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.usecase.CatalogRepresentationTypeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/catalog/representation_type")
@RestController
@RequiredArgsConstructor
public class CatalogRepresentationTypeController {

    private final CatalogRepresentationTypeImpl catalogRepresentationType;

    @GetMapping("/representations")
    public ResponseEntity<Page<CatalogRepresentationTypeModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalogRepresentationType.getAllCatalogsRepresentationTypes(pageable));
    }
    @PostMapping("/create")
    public Optional<CatalogRepresentationTypeModel> createCatalog(@RequestBody CatalogRepresentationTypeModel catalogRepresentationTypeModel){

        return this.catalogRepresentationType.createNewCatalogRepresentationType(catalogRepresentationTypeModel);
    }

    @PostMapping("/delete")
    public Optional<CatalogRepresentationTypeModel> deleteCatalogByAuthor(@RequestBody CatalogRepresentationTypeModel catalogRepresentationTypeModel){
        this.catalogRepresentationType.deleteCatalogRepresentationType(catalogRepresentationTypeModel);
        return Optional.of(catalogRepresentationTypeModel);
    }
}
