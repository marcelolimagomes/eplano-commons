package br.com.eplano.commons.enums;

import br.com.eplano.commons.interfaces.DescricaoEnum;

public enum RegimeTributarioEnum implements DescricaoEnum {
    SIMPLES("Simples Nacional"),
    MEI("Microempreendedor Individual"),
    PRESUMIDO("Lucro Presumido"),
    REAL("Lucro Real"),
    NAO_INFORMADO("Não Informado");

    private final String descricao;

    RegimeTributarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
