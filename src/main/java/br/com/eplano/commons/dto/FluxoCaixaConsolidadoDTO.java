package br.com.eplano.commons.dto;

import java.util.List;

import br.com.eplano.commons.enums.CenarioFluxoCaixaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FluxoCaixaConsolidadoDTO {
    // Campo Calculado: Representa o total de meses que serão projetados no fluxo de
    // caixa. Ex: 12 meses
    private Integer totalMeses;

    // Campo Calculado: Representa cada mês de referência do fluxo de caixa
    private List<FluxoCaixaMensalDTO> caixaMes;

    // Campo Calculado: Representa o total de receitas acumuladas em
    // List<FluxoCaixaMensalDTO>
    private Float somaReceitasAcumulado;

    // Campo Calculado: Representa o total de despesas acumuladas em
    // List<FluxoCaixaMensalDTO>
    private Float somaDespesasAcumulado;

    // Campo Calculado: Representa o total de movimentações de caixa acumuladas em
    // List<FluxoCaixaMensalDTO>
    private Float somaCaixaInicial;

    // Campo Calculado: Representa o total de receitas operacionais acumuladas em
    // List<FluxoCaixaMensalDTO>
    private Float somaReceitaOperacional;

    // Campo Calculado: Representa o total de impostos acumulados em
    // List<FluxoCaixaMensalDTO>
    private Float somaImpostosAcumulado;

    // Campo Calculado: Representa o imposto percentual médio
    private Float mediaImpostoPercentual;

    // Origem: Lucro Liquido Acumulado oriundo da somatória incremental de
    // FluxoCaixaMensalDTO, atributo lucroLiquidoMensal. O índice do array
    // representa o mês de referência do fluxo de caixa.
    private Float[] lucroLiquidoAcumulado;

    private CenarioFluxoCaixaEnum cenarioFluxoCaixa;

}
