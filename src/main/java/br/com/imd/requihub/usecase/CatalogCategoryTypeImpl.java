package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.CatalogCategoryTypeRepository;
import br.com.imd.requihub.repository.CatalogRepository;
import br.com.imd.requihub.repository.UserRepository;
import br.com.imd.requihub.usecase.interfaces.ICatalogCategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogCategoryTypeImpl implements ICatalogCategoryType {

    private final CatalogCategoryTypeRepository catalogCategoryTypeRepository;

    private final UserRepository userRepository;
    private final CatalogRepository catalogRepository;

    @Override
    public Optional<CatalogCategoryTypeModel> createNewCatalogType(CatalogCategoryTypeModel catalogCategoryTypeModel) {
        /* Validate fields */
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        if(catalogCategoryTypeRepository.findByTypeAndOwnerCategoryId(catalogCategoryTypeModel.getType(),author.get().getId()).isPresent()
        || catalogCategoryTypeRepository.findByTypeAndIsDefault(catalogCategoryTypeModel.getType(),true).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Essa representação ja está cadastrada");
        }

        if(author.isPresent()){
            catalogCategoryTypeModel.setOwnerCategory(author.get());
            catalogCategoryTypeModel.setIsDefault(false);
            return Optional.of(catalogCategoryTypeRepository.save(catalogCategoryTypeModel));
        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
        }
    }

    @Override
    public Optional<CatalogCategoryTypeModel> deleteCategoryType(final Long categoryId) {

        Optional<CatalogCategoryTypeModel> category = catalogCategoryTypeRepository.findById(categoryId);

        Page<CatalogModel> catalogs = catalogRepository.findAllByCategoryTypeId(category.get().getId(), Pageable.unpaged());


        if(catalogs.getContent().size() == 0){
            catalogCategoryTypeRepository.delete(category.get());
            return category;
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Não é possivel excluir categoria. pois existe algum catalogo associado a essa categoria");
        }

    }

    @Override
    public Page<CatalogCategoryTypeModel> getAllCatalogsTypes(Pageable pageable) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        if(author.isPresent()){
            return catalogCategoryTypeRepository.findCategoryByUserIdAndDefaultFields(author.get().getId(), pageable);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
        }
    }

    @Override
    public Page<CatalogCategoryTypeModel> getAllDefaultCatalogsTypes() {
        return catalogCategoryTypeRepository.findAllByIsDefault(true, Pageable.unpaged());
    }
}
