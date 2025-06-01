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
import br.com.eplano.commons.dto.DespesaDTO;
import br.com.eplano.commons.dto.FluxoCaixaConsolidadoDTO;
import br.com.eplano.commons.dto.ListaProdutosServicosSugeridoDTO;
import br.com.eplano.commons.dto.ProdutoServicoDTO;
import br.com.eplano.commons.dto.ReceitaDTO;
import br.com.eplano.commons.dto.StatusWorkflowDTO;
import br.com.eplano.commons.dto.SugerirProdutosServicosRequestDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface ApiFinanceiroClient {
    // AIController
    @PostMapping("/api/v1/ai/sugerir-produtos-servicos")
    ListaProdutosServicosSugeridoDTO sugerirProdutosServicos(@RequestBody SugerirProdutosServicosRequestDTO request);

    // ProdutoServicoController
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

    // ReceitaController
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

    // DespesaController
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

    // CaixaEmpresaController
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

    // FluxoCaixaController
    @GetMapping("/api/v1/fluxo-caixa")
    FluxoCaixaConsolidadoDTO getFluxoCaixa();

    @GetMapping("/api/v1/fluxo-caixa/cenarios")
    List<FluxoCaixaConsolidadoDTO> getCenariosFluxoCaixa();

    // WorkflowController
    @GetMapping("/api/v1/workflow/status")
    StatusWorkflowDTO getStatusWorkflow();

}
