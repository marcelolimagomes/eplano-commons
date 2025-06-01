package br.com.eplano.commons.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eplano.commons.dto.ConfiguracoesDTO;
import br.com.eplano.commons.dto.EmpresaDTO;
import br.com.eplano.commons.dto.FaixaSimplesNacionalDTO;

@FeignClient(name = "api-configuracao-eplano")
public interface ApiConfigClient {
    // ConfiguracoesController
    @GetMapping("/api/v1/configuracoes")
    Optional<ConfiguracoesDTO> getConfiguracoes();

    // FaixaNacionalController
    @GetMapping("/api/v1/faixa-simples-nacional")
    List<FaixaSimplesNacionalDTO> getAll();

    @GetMapping("/api/v1/faixa-simples-nacional/{id}")
    Optional<FaixaSimplesNacionalDTO> getById(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/faixa-simples-nacional")
    FaixaSimplesNacionalDTO create(@RequestBody FaixaSimplesNacionalDTO dto);

    @PutMapping("/api/v1/faixa-simples-nacional/{id}")
    FaixaSimplesNacionalDTO update(@PathVariable("id") Integer id, @RequestBody FaixaSimplesNacionalDTO dto);

    @DeleteMapping("/api/v1/faixa-simples-nacional/{id}")
    void delete(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/faixa-simples-nacional/calcular-imposto")
    EmpresaDTO calcularImpostoEmpresa(@RequestBody EmpresaDTO empresa);

    // EmpresaController
    @GetMapping("/api/v1/empresa/all")
    List<EmpresaDTO> getAllEmpresas();

    @GetMapping("/api/v1/empresa")
    Optional<EmpresaDTO> getEmpresa();

    @GetMapping("/api/v1/empresa/{id}")
    EmpresaDTO getEmpresaById(@PathVariable("id") Long id);

}