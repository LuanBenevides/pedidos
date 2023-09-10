package io.github.luanBenevides.vendas.service.impl;

import io.github.luanBenevides.vendas.domain.entity.Cliente;
import io.github.luanBenevides.vendas.domain.entity.ItemPedido;
import io.github.luanBenevides.vendas.domain.entity.Pedido;
import io.github.luanBenevides.vendas.domain.entity.Produto;
import io.github.luanBenevides.vendas.domain.repository.ClienteRepository;
import io.github.luanBenevides.vendas.domain.repository.ItemPedidoRepository;
import io.github.luanBenevides.vendas.domain.repository.PedidoRepository;
import io.github.luanBenevides.vendas.domain.repository.ProdutoRepository;
import io.github.luanBenevides.vendas.exception.BusinessException;
import io.github.luanBenevides.vendas.rest.dto.ItemPedidoDTO;
import io.github.luanBenevides.vendas.rest.dto.PedidoDTO;
import io.github.luanBenevides.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido cretePedido(PedidoDTO pedidoDTO) {
        Integer clienteID = pedidoDTO.getCliente();
        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(() -> new BusinessException("O código de cliente é inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, pedidoDTO.getItens());
        repository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> getPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    //Método auxiliar
    private List<ItemPedido> converterItens(Pedido pedido ,List<ItemPedidoDTO> itensPedido) {
        if(itensPedido.isEmpty()) {
            throw new BusinessException("Não é possível realizar um pedido sem itens");
        }

        return itensPedido
                .stream()
                .map(dto -> {
                    Integer produtoID = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(produtoID)
                            .orElseThrow(() -> new BusinessException(
                                    "Código de produto inválido : " + produtoID
                            ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
