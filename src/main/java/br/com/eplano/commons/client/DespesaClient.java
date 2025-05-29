package br.com.eplano.commons.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.DespesaDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface DespesaClient {

    @GetMapping("/api/v1/despesa")
    List<DespesaDTO> getAllDespesas();

    @GetMapping("/api/v1/despesa/{id}")
    DespesaDTO getDespesaById(@PathVariable("id") Long id);

    @PostMapping("/api/v1/despesa")
    DespesaDTO createDespesa(@RequestBody DespesaDTO dto);

    @PutMapping("/api/v1/despesa/{id}")
    DespesaDTO updateDespesa(@PathVariable("id") Long id, @RequestBody DespesaDTO dto);

    @DeleteMapping("/api/v1/despesa/{id}")
    void deleteDespesa(@PathVariable("id") Long id);
}
