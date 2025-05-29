package br.com.eplano.commons.enums;

import br.com.eplano.commons.interfaces.DescricaoEnum;

public enum CenarioFluxoCaixaEnum implements DescricaoEnum {
    NORMAL("Normal"),
    OTIMISTA("Otimista"),
    PESSIMISTA("Pessimista");

    private final String descricao;

    CenarioFluxoCaixaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
