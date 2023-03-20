package br.com.imd.requihub.repository;


import br.com.imd.requihub.model.CatalogTagsSubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CatalogSubjectTagsRepository extends JpaRepository<CatalogTagsSubjectModel, Long> {
}
