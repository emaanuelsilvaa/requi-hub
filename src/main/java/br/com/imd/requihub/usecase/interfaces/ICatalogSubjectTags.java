package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.CatalogTagsSubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICatalogSubjectTags {
    List<CatalogTagsSubjectModel> createNewCatalogTagsSubjects(final List<CatalogTagsSubjectModel> catalogTagsSubjectModels);

    List<CatalogTagsSubjectModel> deleteCatalogTagsSubjects(final List<CatalogTagsSubjectModel> catalogTagsSubjectModel);

    Page<CatalogTagsSubjectModel> getAllCatalogsSubjects(Pageable pageable);
}
