package br.com.eplano.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.eplano.commons.client.UserClient;
import br.com.eplano.commons.dto.UserDTO;
import br.com.eplano.commons.exceptions.UsuarioNaoAutorizadoException;
import br.com.eplano.commons.utils.UserContextUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public abstract class AbstractController {

    private UserClient userClient;

    @Autowired
    protected void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

    protected UserDTO getCurrentUser() {
        // Tentar primeiro pelo contexto do Gateway
        String email = UserContextUtils.getCurrentUserEmail();
        log.debug("[AbstractController]: Obtendo usuário logado: {}", email);
        if (email != null) {
            UserDTO user = userClient.getUserByEmail(email).orElse(null);
            if (user != null)
                return user;
        }

        throw new UsuarioNaoAutorizadoException();
    }

    protected String getCurrentUserEmail() {
        return UserContextUtils.getCurrentUserEmail();
    }

    protected boolean isCurrentUserAdmin() {
        return UserContextUtils.isAdmin();
    }
}
