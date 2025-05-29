package br.com.eplano.commons.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.eplano.commons.dto.FluxoCaixaConsolidadoDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface FluxoCaixaClient {

    @GetMapping("/api/v1/fluxo-caixa")
    FluxoCaixaConsolidadoDTO getFluxoCaixa();

    @GetMapping("/api/v1/fluxo-caixa/cenarios")
    List<FluxoCaixaConsolidadoDTO> getCenariosFluxoCaixa();
}
