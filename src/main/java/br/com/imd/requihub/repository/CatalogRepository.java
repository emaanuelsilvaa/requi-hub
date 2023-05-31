package br.com.imd.requihub.repository;


import br.com.imd.requihub.model.CatalogModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@EnableJpaRepositories
@Transactional
public interface CatalogRepository extends JpaRepository<CatalogModel, Long> {

    Page<CatalogModel> findAllByAuthorId(Long id, Pageable pageable);

    @Query(value = "SELECT * FROM t_catalog tc  " +
            "join users u on tc.author_id = u.id and u.email ilike %:userId% " +
            "join t_catalog_category_type tcct on tc.category_type_id = tcct.id and tcct.type ilike %:categoryType% " +
            "join t_catalog_representation_type tcrt on tc.representation_type_id = tcrt.id and tcrt.type ilike %:representationType% " +
            "join t_catalog_tag_subject tctss on tc.id = tctss.catalog_model_id and tctss.tag_name ilike %:tag1% and tctss.tag_name ilike %:tag2% " +
            " and tctss.tag_name ilike %:tag3% and tctss.tag_name ilike %:tag4% and tctss.tag_name ilike %:tag5% " +
            "WHERE tc.title ilike %:title%", nativeQuery=true)
    Page<CatalogModel> findByFilter(@Param("userId") String userId,
                                    @Param("title") String title,
                                    @Param("categoryType") String categoryType,
                                    @Param("representationType") String representationType,
                                    @Param("tag1") String tag1,
                                    @Param("tag2") String tag2,
                                    @Param("tag3") String tag3,
                                    @Param("tag4") String tag4,
                                    @Param("tag5") String tag5,
                                    Pageable pageable);


    @Query(value = "SELECT * FROM t_catalog tc  " +
            "join users u on tc.author_id = u.id and u.email ilike %:userId% " +
            "join t_catalog_category_type tcct on tc.category_type_id = tcct.id and tcct.type ilike %:categoryType% " +
            "join t_catalog_representation_type tcrt on tc.representation_type_id = tcrt.id and tcrt.type ilike %:representationType% " +
            "WHERE tc.title ilike %:title%", nativeQuery=true)
    Page<CatalogModel> findByFilterNoTags(@Param("userId") String userId,
                                          @Param("title") String title,
                                          @Param("categoryType") String categoryType,
                                          @Param("representationType") String representationType,
                                          Pageable pageable);
}
