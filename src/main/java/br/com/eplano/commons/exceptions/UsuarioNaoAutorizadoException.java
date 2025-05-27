package br.com.eplano.commons.exceptions;

public class UsuarioNaoAutorizadoException extends RuntimeException {
    public UsuarioNaoAutorizadoException() {
        super("Sessão expirada. Faça login novamente.");
    }
}
