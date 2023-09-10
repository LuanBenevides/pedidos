package io.github.luanBenevides.vendas.rest.controller;

import io.github.luanBenevides.vendas.domain.entity.ItemPedido;
import io.github.luanBenevides.vendas.domain.entity.Pedido;
import io.github.luanBenevides.vendas.rest.dto.InformacoesItemPedidoDTO;
import io.github.luanBenevides.vendas.rest.dto.InformacoesPedidoDTO;
import io.github.luanBenevides.vendas.rest.dto.PedidoDTO;
import io.github.luanBenevides.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private PedidoService service;

    public PedidosController(PedidoService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido savedPedido = service.cretePedido(pedidoDTO);
        return savedPedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .getPedidoCompleto(id)
                .map((pedido -> converter(pedido)))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido não encontrado na base!"
                ));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converterListaDeItens(pedido.getItens()))
                .build();
    }

    public List<InformacoesItemPedidoDTO> converterListaDeItens(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(
                itemPedido -> InformacoesItemPedidoDTO.builder()
                    .descricao(itemPedido.getProduto().getDescricao())
                    .precoUnitario(itemPedido.getProduto().getPrecoUnitario())
                    .quantidade(itemPedido.getQuantidade())
                    .build()
        ).collect(Collectors.toList());
    }
}
