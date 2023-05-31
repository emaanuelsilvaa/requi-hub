package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.entity.Catalog;
import br.com.imd.requihub.model.CatalogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICatalog {
    Optional<CatalogModel> createNewCatalog(final CatalogModel catalogModel);

    Optional<CatalogModel> updateCatalog(final CatalogModel catalogModel);

    Optional<CatalogModel> deleteCatalog(final Long id);

    Page<CatalogModel> getAllCatalogs(Pageable pageable);

    Page<CatalogModel> getCatalogsByAuthor(final Long author, Pageable pageable);

    Page<CatalogModel> getCatalogsByFilter(String userId, String title, String categoryType, String representationType, List<String> subjectTags, Pageable pageable);

    Optional<Catalog> findCatalogById(final Long id);
}
