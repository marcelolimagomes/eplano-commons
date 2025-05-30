package br.com.eplano.commons.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.eplano.commons.dto.ConfiguracoesDTO;

@FeignClient(name = "api-configuracao-eplano") 
public interface ConfiguracoesClient {

    @GetMapping("/api/v1/configuracoes")
    Optional<ConfiguracoesDTO> getConfiguracoes();

}