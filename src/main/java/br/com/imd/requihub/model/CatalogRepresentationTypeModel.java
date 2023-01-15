package br.com.imd.requihub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "representationTypeModel", cascade = CascadeType.ALL)
    private Set<CatalogModel> catalogModels;
}
