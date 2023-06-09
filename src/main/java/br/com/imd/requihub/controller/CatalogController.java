package br.com.imd.requihub.controller;


import br.com.imd.requihub.entity.Catalog;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.usecase.CatalogManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogManagerImpl catalog;

    @GetMapping
    public ResponseEntity<Page<CatalogModel>> getAllCatalogs(Pageable pageable){
        return ResponseEntity.ok(this.catalog.getAllCatalogs(pageable));
    }

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

    @PostMapping("/create")
    public ResponseEntity<Optional<CatalogModel>> createCatalog(@RequestBody CatalogModel catalogModel){

        //return ResponseEntity.ok(catalog.findCatalogById(new Long(16)));
        return ResponseEntity.ok(this.catalog.createNewCatalog(catalogModel));
    }

    @PutMapping("/update")
    public Optional<CatalogModel> updateCatalog(@RequestBody CatalogModel catalogModel){
        return this.catalog.updateCatalog(catalogModel);
    }

    @DeleteMapping("/delete")
    public Optional<CatalogModel> deleteCatalogByAuthor(@RequestParam(value = "id", required = false) Long catalogId){
        return this.catalog.deleteCatalog(catalogId);
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<Catalog>> findCatalogById(@PathVariable Long id){
        return ResponseEntity.ok( catalog.findCatalogById(id) );
    }

    @GetMapping("/find/by/author")
    public ResponseEntity<Page<CatalogModel>> findCatalogByAuthor(@RequestParam(value = "userId", required = false) Long author, Pageable pageable){
        return ResponseEntity.ok(this.catalog.getCatalogsByAuthor(author, pageable));
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
