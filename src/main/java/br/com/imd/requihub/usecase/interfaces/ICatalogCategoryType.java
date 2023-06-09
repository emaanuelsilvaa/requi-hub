package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICatalogCategoryType {
    Optional<CatalogCategoryTypeModel> createNewCatalogType(final CatalogCategoryTypeModel catalogCategoryTypeModel);

    Optional<CatalogCategoryTypeModel> deleteCategoryType(final Long categoryId);

    Page<CatalogCategoryTypeModel> getAllCatalogsTypes(Pageable pageable);

    Page<CatalogCategoryTypeModel> getAllDefaultCatalogsTypes();
}
