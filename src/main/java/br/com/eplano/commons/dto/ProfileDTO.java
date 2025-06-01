package br.com.eplano.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO implements EntityDTO {
    private Long id;
    private String name;
    private String bio;
    private Boolean notifications = false;
    private Integer consultasIARealizadas;
    private Integer countNotificacoesLeadUsuario = 0;
    @ToString.Exclude
    private byte[] photo;

}