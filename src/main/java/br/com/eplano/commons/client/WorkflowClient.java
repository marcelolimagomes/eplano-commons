package br.com.eplano.commons.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.eplano.commons.dto.StatusWorkflowDTO;

@FeignClient(name = "api-financeiro-eplano")
public interface WorkflowClient {

    @GetMapping("/api/v1/workflow/status")
    StatusWorkflowDTO getStatusWorkflow();

}
