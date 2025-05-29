package br.com.eplano.commons.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.CaixaEmpresaDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface CaixaEmpresaClient {

    @GetMapping("/api/v1/caixa-empresa")
    List<CaixaEmpresaDTO> getAllCaixas();

    @GetMapping("/api/v1/caixa-empresa/{id}")
    CaixaEmpresaDTO getCaixaById(@PathVariable("id") Long id);

    @PostMapping("/api/v1/caixa-empresa")
    CaixaEmpresaDTO createCaixa(@RequestBody CaixaEmpresaDTO dto);

    @PutMapping("/api/v1/caixa-empresa/{id}")
    CaixaEmpresaDTO updateCaixa(@PathVariable("id") Long id, @RequestBody CaixaEmpresaDTO dto);

    @DeleteMapping("/api/v1/caixa-empresa/{id}")
    void deleteCaixa(@PathVariable("id") Long id);
}
