package br.com.eplano.commons.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.eplano.commons.dto.ProfileDTO;
import br.com.eplano.commons.dto.UserDTO;

@FeignClient(name = "api-auth-eplano")
public interface ApiAuthClient {
    // UserController
    @GetMapping("/api/v1/users/email/{email}")
    Optional<UserDTO> getUserByEmail(@PathVariable("email") String email);

    // ProfileController
    @GetMapping("/api/v1/profile/{id}")
    Optional<ProfileDTO> getProfileById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/profile")
    Optional<ProfileDTO> getProfile();

    @PostMapping("/api/v1/profile/incrementar-consultas-ia")
    Optional<ProfileDTO> incrementarConsultasIARealizadas();
}