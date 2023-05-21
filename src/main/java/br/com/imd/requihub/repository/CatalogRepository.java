package br.com.imd.requihub.repository;


import br.com.imd.requihub.model.CatalogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface CatalogRepository extends JpaRepository<CatalogModel, Long> {

    List<CatalogModel> findAllByAuthorId(Long id);
}
