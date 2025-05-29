package br.com.eplano.commons.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa o fluxo de caixa consolidado de um determinado mês.
 * 
 * <p>
 * Esta classe agrega informações financeiras mensais, incluindo receitas,
 * despesas,
 * movimentações de caixa por empresa, lucro líquido mensal e impostos.
 * </p>
 *
 * <p>
 * <b>Referências de entidades relacionadas:</b>
 * <ul>
 * <li>{@link ReceitaDTO} - Lista de receitas do mês, provenientes da entidade
 * Receita.</li>
 * <li>{@link DespesaDTO} - Lista de despesas do mês, provenientes da entidade
 * Despesa.</li>
 * <li>{@link CaixaEmpresaDTO} - Movimentações de caixa por empresa,
 * provenientes da entidade CaixaEmpresa.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <b>Atributos:</b>
 * <ul>
 * <li><b>mes</b>: Mês de referência do fluxo de caixa (campo calculado).</li>
 * <li><b>receitas</b>: Lista de receitas do mês ({@link ReceitaDTO}).</li>
 * <li><b>despesas</b>: Lista de despesas do mês ({@link DespesaDTO}).</li>
 * <li><b>caixaEmpresas</b>: Lista de movimentações de caixa por empresa
 * ({@link CaixaEmpresaDTO}).</li>
 * <li><b>lucroLiquidoMensal</b>: Lucro líquido do mês, calculado como: caixa do
 * 1º mês + receitas - despesas.</li>
 * <li><b>imposto</b>: Valor do imposto referente ao mês, proveniente do
 * atributo Imposto da entidade Empresa.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Esta estrutura facilita a visualização consolidada dos dados financeiros
 * mensais,
 * permitindo análises de desempenho e acompanhamento do fluxo de caixa.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FluxoCaixaMensalDTO {

    // Campo Calculado: Representa o mês de referência do fluxo de caixa
    private Integer mes;

    // Origem: Entidade Receita
    private List<ReceitaDTO> receitas;

    // Origem: Entidade Despesa
    private List<DespesaDTO> despesas;

    // Origem: Entidade CaixaEmpresa
    private List<CaixaEmpresaDTO> caixaEmpresas;

    // Campo calculado: Representa o total de despesas do mês
    private Float somaDespesas;

    // Campo calculado: Representa o total de receitas do mês
    private Float somaReceitas;

    // Campo calculado: Representa o total de movimentações de caixa por empresa
    private Float somaCaixa;

    // Campo calculado: Representa soma de caixa + soma de receitas - soma de
    // despesas
    private float receitaOperacional;

    // Origem: Campo calculado: caixa 1° mês + receita - despesa
    private Float lucroLiquidoMensal;

    // Origem: Entidade Empresa, atributo Imposto
    private Float impostoPercentual;

    // Campo calculado: Imposto = (somaReceita * impostoPercentual) / 100
    private Float impostoValor;
}
