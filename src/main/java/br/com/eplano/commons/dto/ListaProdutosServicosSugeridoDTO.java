package br.com.eplano.commons.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public record ListaProdutosServicosSugeridoDTO(
        @JsonProperty(value = "sugestoes", required = true) @Schema(description = "Lista de sugestões de produtos ou serviços", required = true) List<Sugestao> sugestoes) {
    public static enum TipoProdutoServico {
        @JsonProperty("PRODUTO")
        PRODUTO,
        @JsonProperty("SERVICO")
        SERVICO
    }

    public static record Sugestao(
            @JsonProperty(value = "tipo_produto_servico", required = true) @Schema(description = "Tipo do produto ou serviço. Pode ser 'PRODUTO' ou 'SERVICO'.", required = true) TipoProdutoServico tipoProdutoServico,

            @JsonProperty(value = "nome_produto_servico", required = true) @Schema(description = "Nome do produto ou serviço sugerido.", required = true) String nomeProdutoServico,

            @JsonProperty(value = "descricao_produto_servico", required = true) @Schema(description = "Descrição do produto ou serviço sugerido.", required = true) String descricaoProdutoServico) {
    }
}