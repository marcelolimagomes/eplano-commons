package br.com.eplano.commons.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.eplano.commons.dto.EmpresaDTO;

@FeignClient(name = "api-configuracao-eplano") // O nome deve ser igual ao registrado no Eureka pelo serviço de
                                               // configuração
public interface EmpresaClient {

    @GetMapping("/api/v1/empresa/all")
    List<EmpresaDTO> getAllEmpresas();

    @GetMapping("/api/v1/empresa")
    Optional<EmpresaDTO> getEmpresa();

    @GetMapping("/api/v1/empresa/{id}")
    EmpresaDTO getEmpresaById(@PathVariable("id") Long id);

}