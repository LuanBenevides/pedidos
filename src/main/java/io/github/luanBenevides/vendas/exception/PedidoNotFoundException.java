package io.github.luanBenevides.vendas.exception;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException() {
        super("Pedido não encontrado.");
    }
}
