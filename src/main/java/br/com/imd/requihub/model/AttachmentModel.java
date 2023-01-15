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
@Table(name = "T_ATTACHMENT")
public class AttachmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String attachmentLink;

    private String fileType;

    private String fileSize;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "attachmentModel", cascade = CascadeType.ALL)
    private CatalogModel catalogModel;

}
