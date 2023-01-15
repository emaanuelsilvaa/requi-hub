package br.com.imd.requihub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CATALOG_TAG_SUBJECT")
public class CatalogTagsSubjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tagName;

    private String catalogOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalogModel_id", referencedColumnName = "ID")
    private CatalogModel catalogModel;

}
