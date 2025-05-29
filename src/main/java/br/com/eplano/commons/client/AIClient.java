package br.com.eplano.commons.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.ListaProdutosServicosSugeridoDTO;
import br.com.eplano.commons.dto.SugerirProdutosServicosRequestDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface AIClient {

    @PostMapping("/api/v1/ai/sugerir-produtos-servicos")
    ListaProdutosServicosSugeridoDTO sugerirProdutosServicos(@RequestBody SugerirProdutosServicosRequestDTO request);

}
