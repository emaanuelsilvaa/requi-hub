package br.com.imd.requihub.entity;

import br.com.imd.requihub.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Catalog {

    private Long id;

    private ZonedDateTime creationTime;

    private ZonedDateTime lastEdited;

    private String version;

    private String title;

    private String description;

    private Boolean enabled = true;

    private Attachment attachment;

    private Author author;

    private String bibliographicReference;

    private CatalogCategoryTypeModel categoryType;

    private Set<CatalogEvaluationModel> catalogEvaluationModels;

    private CatalogRepresentationTypeModel representationTypeModel;

    private Set<CatalogTagsSubjectModel> subjectTags;
}
