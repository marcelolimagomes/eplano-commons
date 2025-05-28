package br.com.eplano.commons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SegmentoNegocioDTO {
    private Integer id;
    private String codigo;
    private String descricao;
    private EnumNameValueDTO segmentoEmpresa;
    private ConfiguracoesDTO configuracoes;
}
