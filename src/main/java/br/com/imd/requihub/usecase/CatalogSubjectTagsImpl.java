package br.com.imd.requihub.usecase;


import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.CatalogTagsSubjectModel;
import br.com.imd.requihub.repository.CatalogSubjectTagsRepository;
import br.com.imd.requihub.usecase.interfaces.ICatalogSubjectTags;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogSubjectTagsImpl implements ICatalogSubjectTags {

    private final CatalogSubjectTagsRepository catalogSubjectTagsRepository;

    @Override
    public List<CatalogTagsSubjectModel> createNewCatalogTagsSubjects(List<CatalogTagsSubjectModel> catalogTagsSubjectModels) {
        catalogSubjectTagsRepository.saveAll(catalogTagsSubjectModels);
        return catalogTagsSubjectModels;
    }

    @Override
    public List<CatalogTagsSubjectModel> deleteCatalogTagsSubjects( List<CatalogTagsSubjectModel> catalogTagsSubjectModel) {
        catalogSubjectTagsRepository.deleteAll(catalogTagsSubjectModel);
        return catalogTagsSubjectModel;
    }

    @Override
    public Page<CatalogTagsSubjectModel> getAllCatalogsSubjects(Pageable pageable) {
        return catalogSubjectTagsRepository.findAll(pageable);
    }
}
