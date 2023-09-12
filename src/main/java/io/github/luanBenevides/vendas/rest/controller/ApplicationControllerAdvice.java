package io.github.luanBenevides.vendas.rest.controller;

import io.github.luanBenevides.vendas.exception.BusinessException;
import io.github.luanBenevides.vendas.exception.PedidoNotFoundException;
import io.github.luanBenevides.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessException(BusinessException exception) {
        String mensageError = exception.getMessage();
        return new ApiErrors(mensageError);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }
}
