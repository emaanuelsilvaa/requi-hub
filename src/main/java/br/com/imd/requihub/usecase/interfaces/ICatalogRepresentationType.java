package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICatalogRepresentationType {
    Optional<CatalogRepresentationTypeModel> createNewCatalogRepresentationType(final CatalogRepresentationTypeModel catalogRepresentationTypeModel);

    Optional<CatalogRepresentationTypeModel> deleteCatalogRepresentationType(final Long representationId);

    Page<CatalogRepresentationTypeModel> getAllCatalogsRepresentationTypes(Pageable pageable);
}
