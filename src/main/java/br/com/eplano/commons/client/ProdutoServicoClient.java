package br.com.eplano.commons.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.ProdutoServicoDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface ProdutoServicoClient {

    @GetMapping("/api/v1/produto-servico")
    List<ProdutoServicoDTO> getAllProdutoServicos();

    @GetMapping("/api/v1/produto-servico/{id}")
    ProdutoServicoDTO getProdutoServicoById(@PathVariable("id") Long id);

    @PostMapping("/api/v1/produto-servico")
    ProdutoServicoDTO createProdutoServico(@RequestBody ProdutoServicoDTO dto);

    @PutMapping("/api/v1/produto-servico/{id}")
    ProdutoServicoDTO updateProdutoServico(@PathVariable("id") Long id, @RequestBody ProdutoServicoDTO dto);

    @DeleteMapping("/api/v1/produto-servico/{id}")
    void deleteProdutoServico(@PathVariable("id") Long id);
}
