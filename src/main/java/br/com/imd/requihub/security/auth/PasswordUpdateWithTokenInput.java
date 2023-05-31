package br.com.imd.requihub.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class PasswordUpdateWithTokenInput {

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("token")
    private String token;
}
