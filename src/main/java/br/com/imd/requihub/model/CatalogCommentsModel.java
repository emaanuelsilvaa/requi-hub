package br.com.imd.requihub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CATALOG_COMMENT")
public class CatalogCommentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ZonedDateTime creationTime;

    private ZonedDateTime lastEdited;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "author_id", referencedColumnName = "ID")
    private UserModel author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "catalogModel_id", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CatalogModel catalogModel;
}
