package br.com.eplano.commons.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.eplano.commons.dto.UserDTO;

@FeignClient(name = "api-auth-eplano") // O nome deve ser igual ao registrado no Eureka pelo serviço de autenticação
public interface UserClient {
    @GetMapping("/api/v1/users/email/{email}")
    Optional<UserDTO> getUserByEmail(@PathVariable("email") String email);
}