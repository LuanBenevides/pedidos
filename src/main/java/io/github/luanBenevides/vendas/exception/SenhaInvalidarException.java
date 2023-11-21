package io.github.luanBenevides.vendas.exception;

public class SenhaInvalidarException extends RuntimeException {
    public SenhaInvalidarException( ){
        super("Senha inválida.");
    }
}
