package br.com.imd.requihub.controller;


import br.com.imd.requihub.dtos.DtoTest;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.usecase.CatalogImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogImpl catalog;

    @GetMapping
    public ResponseEntity<Page<CatalogModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalog.getAllCatalogs(pageable));
    }
    @PostMapping("/create")
    public Optional<CatalogModel> createCatalog(@RequestBody CatalogModel catalogModel){

        return this.catalog.createNewCatalog(catalogModel);
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

    @GetMapping("/find/{author}")
    public ResponseEntity<Page<CatalogModel>> findCatalogByAuthor(@PathVariable String author){
        return ResponseEntity.ok(this.catalog.getCatalogsByAuthor(author));
    }

}