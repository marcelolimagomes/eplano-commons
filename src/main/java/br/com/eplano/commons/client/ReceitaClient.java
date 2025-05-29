package br.com.eplano.commons.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.ReceitaDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface ReceitaClient {

    @GetMapping("/api/v1/receita")
    List<ReceitaDTO> getAllReceitas();

    @GetMapping("/api/v1/receita/{id}")
    ReceitaDTO getReceitaById(@PathVariable("id") Long id);

    @PostMapping("/api/v1/receita")
    ReceitaDTO createReceita(@RequestBody ReceitaDTO dto);

    @PutMapping("/api/v1/receita/{id}")
    ReceitaDTO updateReceita(@PathVariable("id") Long id, @RequestBody ReceitaDTO dto);

    @DeleteMapping("/api/v1/receita/{id}")
    void deleteReceita(@PathVariable("id") Long id);
}
