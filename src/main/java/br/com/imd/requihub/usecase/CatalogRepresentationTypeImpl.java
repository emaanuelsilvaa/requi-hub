package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.CatalogRepresentationTypeRepository;
import br.com.imd.requihub.repository.UserRepository;
import br.com.imd.requihub.usecase.interfaces.ICatalogRepresentationType;
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
public class CatalogRepresentationTypeImpl implements ICatalogRepresentationType {

    private final CatalogRepresentationTypeRepository catalogRepresentationTypeRepository;

    private final UserRepository userRepository;

    @Override
    public Optional<CatalogRepresentationTypeModel> createNewCatalogRepresentationType(CatalogRepresentationTypeModel catalogRepresentationTypeModel) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);
        if(author.isPresent()){
            catalogRepresentationTypeModel.setOwner(author.get());
            return Optional.of(catalogRepresentationTypeRepository.save(catalogRepresentationTypeModel));
        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
        }
    }

    @Override
    public Optional<CatalogRepresentationTypeModel> deleteCatalogRepresentationType(CatalogRepresentationTypeModel catalogRepresentationTypeModel) {
        catalogRepresentationTypeRepository.delete(catalogRepresentationTypeModel);
        return Optional.of(catalogRepresentationTypeModel);
    }

    @Override
    public Page<CatalogRepresentationTypeModel> getAllCatalogsRepresentationTypes(Pageable pageable) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        if(author.isPresent()){
            return catalogRepresentationTypeRepository.findRepresentationByUserIdAndDefaultFields(author.get().getId(), pageable);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
        }
    }
}
