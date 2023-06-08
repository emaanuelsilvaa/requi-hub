package br.com.imd.requihub.repository;

import br.com.imd.requihub.model.CatalogCategoryTypeModel;
import br.com.imd.requihub.model.CatalogRepresentationTypeModel;
import br.com.imd.requihub.model.UserModel;
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
public interface CatalogCategoryTypeRepository extends JpaRepository<CatalogCategoryTypeModel, Long> {
    Optional<CatalogCategoryTypeModel> findByType(String type);

    @Query(value = "SELECT DISTINCT tcrt.type, tcrt.id, tcrt.is_default, tcrt.owner_id  from t_catalog_category_type tcrt " +
            "join users u on tcrt.owner_id = :userId or tcrt.is_default = true " +
            "where tcrt.\"type\" ilike '%%' ", nativeQuery=true )
    Page<CatalogCategoryTypeModel> findCategoryByUserIdAndDefaultFields(@Param("userId") Long representationType,
                                                                                    Pageable pageable);
}
