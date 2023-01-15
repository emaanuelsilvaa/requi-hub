package br.com.imd.requihub.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CATALOG")
public class CatalogModel {

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

    @OneToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "attachment_id", referencedColumnName = "ID")
    private AttachmentModel attachmentModel;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "author_id", referencedColumnName = "ID")
    private UserModel author;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "categoryType_id", referencedColumnName = "ID")
    private CatalogCategoryTypeModel categoryType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", cascade = CascadeType.ALL)
    private Set<CatalogEvaluationModel> catalogEvaluationModels;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "representationType_id", referencedColumnName = "ID")
    private CatalogRepresentationTypeModel representationTypeModel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", cascade = CascadeType.ALL)
    private Set<CatalogTagsSubjectModel> subjectTags;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogModel", cascade = CascadeType.ALL)
    private Set<CatalogCommentsModel> commentsModels;

}
