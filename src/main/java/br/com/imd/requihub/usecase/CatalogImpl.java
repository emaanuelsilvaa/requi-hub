package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.CatalogRepository;
import br.com.imd.requihub.repository.UserRepository;
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

@Service
@RequiredArgsConstructor
public class CatalogImpl implements ICatalog {

    private final UserRepository userRepository;

    private final CatalogRepository catalogRepository;

    @Override
    public Optional<CatalogModel> createNewCatalog(CatalogModel catalogModel) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        final Optional<UserModel> author = userRepository.findByEmail(email);

        /* find category type */
        /* find representation type */
        /* find tags */

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
    public Page<CatalogModel> getCatalogsByAuthor(String author) {
        return new PageImpl<>(catalogRepository.findAllByAuthor(author));
    }
}
