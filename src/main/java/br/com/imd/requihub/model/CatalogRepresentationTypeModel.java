package br.com.imd.requihub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CATALOG_REPRESENTATION_TYPE")
public class CatalogRepresentationTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private Boolean isDefault;


    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST )
    @JoinColumn(name = "owner_id", referencedColumnName = "ID")
    private UserModel owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "representationTypeModel")
    @JsonIgnore
    private Set<CatalogModel> catalogModels;
}
