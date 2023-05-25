package br.com.imd.requihub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Author {

    private Long id;

    private String firstName;

    private String lastName;

    private String about;

    private String email;

}
