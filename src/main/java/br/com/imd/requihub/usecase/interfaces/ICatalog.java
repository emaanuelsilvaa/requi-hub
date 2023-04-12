package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.model.CatalogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICatalog {
    Optional<CatalogModel> createNewCatalog(final CatalogModel catalogModel);

    Optional<CatalogModel> updateCatalog(final CatalogModel catalogModel);

    Optional<CatalogModel> deleteCatalog(final CatalogModel catalogModel);

    Page<CatalogModel> getAllCatalogs(Pageable pageable);

    Page<CatalogModel> getCatalogsByAuthor(final String author);

    Optional<CatalogModel> findCatalogById(final Long id);
}
