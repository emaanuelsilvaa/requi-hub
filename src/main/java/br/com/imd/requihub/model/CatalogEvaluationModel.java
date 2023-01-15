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
@Table(name = "T_CATALOG_EVALUATION")
public class CatalogEvaluationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String evaluation_type;

    private String evaluationDescription;

    private long evaluationRate;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "catalogModel_id", referencedColumnName = "ID")
    private CatalogModel catalogModel;




}
