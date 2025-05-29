package br.com.eplano.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatusWorkflowDTO {
    private final EmpresaDTO empresa;
    private final Integer quantidadeProdutosServicos;
    private final Integer quantidadeReceitas;
    private final Integer quantidadeDespesas;
    private final Integer quantidadeCaixaEmpresa;

    @JsonProperty
    public Boolean hasProdutoServicos() {
        return quantidadeProdutosServicos > 0;
    };

    @JsonProperty
    public Boolean hasReceitas() {
        return quantidadeReceitas > 0;
    };

    @JsonProperty
    public Boolean hasDespesas() {
        return quantidadeDespesas > 0;
    };

    @JsonProperty
    public Boolean hasCaixaEmpresa() {
        return quantidadeCaixaEmpresa > 0;
    };

    @JsonProperty
    public Boolean hasCadastroIncompleto() {
        return hasProdutoServicos() || hasReceitas() || hasDespesas() || hasCaixaEmpresa();
    }

}
