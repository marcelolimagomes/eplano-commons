package br.com.eplano.commons.dto;

import lombok.Data;

@Data
public class ProdutoServicoDTO implements EntityDTO {
    private Long id;
    private String nomeProdutoServico;
    private String descricaoProdutoServico;
    private Long empresaId;
    // private List<ReceitaDTO> receitas;
    private EnumNameValueDTO tipoProdutoServico;
}