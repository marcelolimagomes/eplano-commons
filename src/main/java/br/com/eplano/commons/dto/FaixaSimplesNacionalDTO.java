package br.com.eplano.commons.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FaixaSimplesNacionalDTO implements EntityDTO {
    private Integer id;
    private String anexo;
    private String faixa;
    private Float aliquota;
    private Float valorDeduzir;
    private Float receitaBrutaInicial;
    private Float receitaBrutaFinal;
    private EnumNameValueDTO segmentoEmpresa;
}
