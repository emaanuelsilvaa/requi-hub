package br.com.imd.requihub.repository;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CatalogCategoryRepository extends JpaRepository<CatalogCategoryTypeModel, Long> {
}
