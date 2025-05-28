package br.com.eplano.commons.interfaces;

/**
 * Interface que define o contrato para Enums que possuem descrição.
 * Todos os Enums do projeto devem implementar esta interface para
 * garantir padronização na conversão para DTOs.
 */
public interface DescricaoEnum {
    /**
     * Retorna a descrição do valor do enum.
     * 
     * @return A descrição do enum
     */
    String getDescricao();

    String name();
}
