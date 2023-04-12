package br.com.imd.requihub.controller;


import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.usecase.CatalogManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogManagerImpl catalog;

    @GetMapping
    public ResponseEntity<Page<CatalogModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalog.getAllCatalogs(pageable));
    }
    @PostMapping("/create")
    public ResponseEntity<Optional<CatalogModel>> createCatalog(@RequestBody CatalogModel catalogModel){
        ;
        return ResponseEntity.ok(catalog.findCatalogById(new Long(16)));
        //return ResponseEntity.ok(this.catalog.createNewCatalog(catalogModel));
    }

    @PutMapping("/update")
    public Optional<CatalogModel> updateCatalog(@RequestBody CatalogModel catalogModel){
        return this.catalog.updateCatalog(catalogModel);
    }

    @PostMapping("/delete")
    public Optional<CatalogModel> deleteCatalogByAuthor(@RequestBody CatalogModel catalogModel){
        this.catalog.deleteCatalog(catalogModel);
        return Optional.of(catalogModel);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<CatalogModel>> findCatalogById(@PathVariable Long id){
        return ResponseEntity.ok( catalog.findCatalogById(id) );
    }

    @GetMapping("/find/{author}")
    public ResponseEntity<Page<CatalogModel>> findCatalogByAuthor(@PathVariable String author){
        return ResponseEntity.ok(this.catalog.getCatalogsByAuthor(author));
    }

    @GetMapping("/find/{representationType}")
    public ResponseEntity<Page<CatalogModel>> findCatalogByRepresentationType(@PathVariable String representationType){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/find/{categoryType}")
    public ResponseEntity<Page<CatalogModel>> findCatalogByCategoryType(@PathVariable String categoryType){
        return ResponseEntity.ok(null);
    }




}
