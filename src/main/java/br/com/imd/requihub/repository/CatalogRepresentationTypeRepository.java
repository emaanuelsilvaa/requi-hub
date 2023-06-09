package br.com.imd.requihub.repository;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CatalogRepresentationTypeRepository extends JpaRepository<CatalogRepresentationTypeModel, Long> {
    Optional<CatalogRepresentationTypeModel> findByType(String type);

    Optional<CatalogRepresentationTypeModel> findByTypeAndOwnerId(String type, long id);

    @Query(value = "SELECT DISTINCT tcrt.type, tcrt.id, tcrt.is_default, tcrt.owner_id  from t_catalog_representation_type tcrt " +
            "join users u on tcrt.owner_id = :userId or tcrt.is_default = true " +
            "where tcrt.\"type\" ilike '%%' ", nativeQuery=true )
    Page<CatalogRepresentationTypeModel> findRepresentationByUserIdAndDefaultFields(@Param("userId") Long representationType,
                                          Pageable pageable);
}
