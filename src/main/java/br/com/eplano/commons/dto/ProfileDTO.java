package br.com.eplano.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProfileDTO {
    private Long id;
    private String name;
    private Integer consultasIARealizadas;
    private Integer countNotificacoesLeadUsuario = 0;

    // Construtor padrão
    public ProfileDTO() {
    }

}