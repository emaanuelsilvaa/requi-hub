package br.com.imd.requihub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ATTACHMENT")
public class AttachmentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "attachment_link", length = 1024)
    private String attachmentLink;

    @Column(name = "thumbnail_link")
    private String thumbnailLink;

    private String fileType;

    private long fileSize;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "attachment", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CatalogModel catalogModel;

}
