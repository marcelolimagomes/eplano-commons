package br.com.eplano.commons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CaixaEmpresaDTO implements EntityDTO {
    private Long id;
    private String nome;
    private Float valor;
    private Long empresaId;
    private EnumNameValueDTO tipo;

}
