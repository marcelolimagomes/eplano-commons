package br.com.eplano.commons.dto;

import lombok.Data;

@Data
public class DespesaDTO implements EntityDTO {
    private Long id;
    private String nomeDespesa;
    private Float valor;
    private Long empresaId;
    private EnumNameValueDTO categoria;
}
