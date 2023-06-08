package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.CatalogRepository;
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

    private final CatalogRepository catalogRepository;

    @Override
    public Optional<CatalogRepresentationTypeModel> createNewCatalogRepresentationType(CatalogRepresentationTypeModel catalogRepresentationTypeModel) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        if(catalogRepresentationTypeRepository.findByType(catalogRepresentationTypeModel.getType()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Essa representação ja está cadastrada");
        }
        if(author.isPresent()){
            catalogRepresentationTypeModel.setOwner(author.get());
            catalogRepresentationTypeModel.setIsDefault(false);
            return Optional.of(catalogRepresentationTypeRepository.save(catalogRepresentationTypeModel));
        }else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Author not found");
        }
    }

    @Override
    public Optional<CatalogRepresentationTypeModel> deleteCatalogRepresentationType(Long representationId) {

        Optional<CatalogRepresentationTypeModel> representationTypeModel = catalogRepresentationTypeRepository.findById(representationId);

        Page<CatalogModel> catalogs = catalogRepository.findAllByRepresentationTypeModelId(representationTypeModel.get().getId(), Pageable.unpaged());

        if(catalogs.getContent().size() == 0){
            catalogRepresentationTypeRepository.delete(representationTypeModel.get());
            return representationTypeModel;
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Não é possivel excluir categoria. pois existe algum catalogo associado a essa categoria");
        }

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
