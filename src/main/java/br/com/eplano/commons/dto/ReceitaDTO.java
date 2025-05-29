package br.com.eplano.commons.dto;

import lombok.Data;

@Data

public class ReceitaDTO implements EntityDTO{
    private Long id;
    private Float precoVenda;
    private Integer quantidadeEstimada;
    private Float valorReceita;
    private Long empresaId;
    private ProdutoServicoDTO produtoServico;
}
