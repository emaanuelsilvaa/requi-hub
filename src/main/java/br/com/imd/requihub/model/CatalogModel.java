package br.com.imd.requihub.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CATALOG")
public class CatalogModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ZonedDateTime creationTime;

    private ZonedDateTime lastEdited;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean enabled = true;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER, optional=true )
    @JoinColumn(name = "attachment_id", referencedColumnName = "ID")
    private AttachmentModel attachment;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "author_id", referencedColumnName = "ID")
    private UserModel author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "categoryType_id", referencedColumnName = "ID")
    private CatalogCategoryTypeModel categoryType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", orphanRemoval=true)
    private Set<CatalogEvaluationModel> catalogEvaluationModels;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "representationType_id", referencedColumnName = "ID")
    private CatalogRepresentationTypeModel representationTypeModel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", orphanRemoval=true)
    private Set<CatalogTagsSubjectModel> subjectTags;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", orphanRemoval=true)
    private Set<CatalogCommentsModel> commentsModels;


}
