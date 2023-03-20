package br.com.imd.requihub.repository;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CatalogRepresentationTypeRepository extends JpaRepository<CatalogRepresentationTypeModel, Long> {
    Optional<CatalogRepresentationTypeModel> findByType(String type);
}
