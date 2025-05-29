package br.com.eplano.commons.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.eplano.commons.dto.ProfileDTO;

@FeignClient(name = "api-auth-eplano")
public interface ProfileClient {

    @GetMapping("/api/v1/profile/{id}")
    Optional<ProfileDTO> getProfileById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/profile")
    Optional<ProfileDTO> getProfile();

    @PatchMapping("/api/v1/profile/incrementar-consultas-ia")
    Optional<ProfileDTO> incrementarConsultasIARealizadas();

    // Adicione outros endpoints conforme o controller evoluir
}
