package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.*;
import br.com.imd.requihub.repository.*;
import br.com.imd.requihub.usecase.interfaces.ICatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogManagerImpl implements ICatalog {

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    private final CatalogRepresentationTypeRepository catalogRepresentationTypeRepository;

    private final CatalogSubjectTagsRepository catalogSubjectTagsRepository;

    private final CatalogCategoryTypeRepository catalogCategoryTypeRepository;

    @Override
    public Optional<CatalogModel> createNewCatalog(CatalogModel catalogModel) {

        // validate information

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        /* find category type */
        final Optional<CatalogCategoryTypeModel> categoryTypeModel = catalogCategoryTypeRepository.findByType(catalogModel.getCategoryType().getType());
        /* find representation type */
        final Optional<CatalogRepresentationTypeModel> representationTypeModel = catalogRepresentationTypeRepository.findByType(catalogModel.getRepresentationTypeModel().getType());

        /* Initial Attachment*/
        catalogModel.setAttachment(catalogModel.getAttachment());
        /* find tags */
        catalogModel.setCategoryType(categoryTypeModel.get());

        catalogModel.setRepresentationTypeModel(representationTypeModel.get());

        catalogModel.setEnabled(true);

        catalogModel.setVersion("0.0."+1);

        /* config evaluations */

        if(author.isPresent()){
            catalogModel.setAuthor(author.get());
            return Optional.of(catalogRepository.save(catalogModel));
        }
        else
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
    }

    @Override
    public Optional<CatalogModel> updateCatalog(CatalogModel catalogModel) {
        return Optional.of(catalogRepository.save(catalogModel));
    }

    @Override
    public Optional<CatalogModel> deleteCatalog(CatalogModel catalogModel) {
        catalogRepository.delete(catalogModel);
        return Optional.of(catalogModel);
    }

    @Override
    public Page<CatalogModel> getAllCatalogs(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }

    @Override
    public Page<CatalogModel> getCatalogsByAuthor(Long author) {
        return new PageImpl<>(catalogRepository.findAllByAuthorId(author));
    }

    @Override
    public Optional<CatalogModel> findCatalogById(Long id) {
        return catalogRepository.findById(id);
    }
}
