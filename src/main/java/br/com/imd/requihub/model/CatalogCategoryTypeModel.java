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
@Table(name = "T_CATALOG_CATEGORY_TYPE")
public class CatalogCategoryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String type;

    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST )
    @JoinColumn(name = "owner_id", referencedColumnName = "ID")
    private UserModel ownerCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryType")
    @JsonIgnore
    private Set<CatalogModel> catalogModel;
}
