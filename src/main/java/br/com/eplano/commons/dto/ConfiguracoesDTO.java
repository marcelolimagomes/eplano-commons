package br.com.eplano.commons.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ConfiguracoesDTO implements EntityDTO {
    private Integer id;
    private Integer quantidadeMesesProjecaoFluxoCaixa;
    private Float fluxoCaixaPessimista;
    private Float fluxoCaixaOtimista;
    private Float limiteFaturamentoAnualMEI;
    private Float valorImpostoMEI;
    private Integer limiteConsultasIA;
    private Integer limiteNotificacoesLeadUsuario;
    private Boolean exibirFluxoCaixa;
    // private List<SegmentoNegocioDTO> segmentosNegocio;
}
