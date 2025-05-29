package br.com.eplano.commons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO implements EntityDTO{
    private Long id;
    private String name;
}
