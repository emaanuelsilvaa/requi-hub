package br.com.imd.requihub.repository;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CatalogCategoryTypeRepository extends JpaRepository<CatalogCategoryTypeModel, Long> {
    Optional<CatalogCategoryTypeModel> findByType(String type);
}
