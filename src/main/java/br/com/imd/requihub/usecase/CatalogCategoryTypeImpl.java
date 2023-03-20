package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.CatalogCategoryTypeRepository;
import br.com.imd.requihub.usecase.interfaces.ICatalogCategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogCategoryTypeImpl implements ICatalogCategoryType {

    private final CatalogCategoryTypeRepository catalogCategoryTypeRepository;

    @Override
    public Optional<CatalogCategoryTypeModel> createNewCatalogType(CatalogCategoryTypeModel catalogCategoryTypeModel) {
        /* Validate fields */
        return Optional.of(catalogCategoryTypeRepository.save(catalogCategoryTypeModel));
    }

    @Override
    public Optional<CatalogCategoryTypeModel> deleteCatalogType(CatalogCategoryTypeModel catalogCategoryTypeModel) {
        catalogCategoryTypeRepository.delete(catalogCategoryTypeModel);
        return Optional.of(catalogCategoryTypeModel);
    }

    @Override
    public Page<CatalogCategoryTypeModel> getAllCatalogsTypes(Pageable pageable) {
        return catalogCategoryTypeRepository.findAll(pageable);
    }
}
