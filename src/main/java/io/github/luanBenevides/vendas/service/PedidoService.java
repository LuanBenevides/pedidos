package io.github.luanBenevides.vendas.service;

import io.github.luanBenevides.vendas.domain.entity.Pedido;
import io.github.luanBenevides.vendas.rest.dto.PedidoDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface PedidoService {
    Pedido cretePedido(PedidoDTO pedidoDTO);

    Optional<Pedido> getPedidoCompleto(Integer id);
}
