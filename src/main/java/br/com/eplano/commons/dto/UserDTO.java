package br.com.eplano.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private Long id;
    private String email;
    private ProfileDTO profile;

    // Construtores
    public UserDTO() {
    }
}