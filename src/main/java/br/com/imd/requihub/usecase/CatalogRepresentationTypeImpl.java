package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.repository.CatalogRepresentationTypeRepository;
import br.com.imd.requihub.usecase.interfaces.ICatalogRepresentationType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CatalogRepresentationTypeImpl implements ICatalogRepresentationType {

    private final CatalogRepresentationTypeRepository catalogRepresentationTypeRepository;

    @Override
    public Optional<CatalogRepresentationTypeModel> createNewCatalogRepresentationType(CatalogRepresentationTypeModel catalogRepresentationTypeModel) {
        return Optional.of(catalogRepresentationTypeRepository.save(catalogRepresentationTypeModel));
    }

    @Override
    public Optional<CatalogRepresentationTypeModel> deleteCatalogRepresentationType(CatalogRepresentationTypeModel catalogRepresentationTypeModel) {
        catalogRepresentationTypeRepository.delete(catalogRepresentationTypeModel);
        return Optional.of(catalogRepresentationTypeModel);
    }

    @Override
    public Page<CatalogRepresentationTypeModel> getAllCatalogsRepresentationTypes(Pageable pageable) {
        return catalogRepresentationTypeRepository.findAll(pageable);
    }
}
