package io.github.luanBenevides.vendas.testUtils;

import io.github.luanBenevides.vendas.rest.dto.ItemPedidoDTO;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.context.properties.bind.Bindable.listOf;

public class TestUtils {

    public static List<ItemPedidoDTO> criaListaDeItens() {
        List<ItemPedidoDTO> listaDeItens = new ArrayList<>();
        ItemPedidoDTO itemParatestesUm = new ItemPedidoDTO(1, 10);
        ItemPedidoDTO itemParatestesDois = new ItemPedidoDTO(2, 20);
        ItemPedidoDTO itemParatestesTres = new ItemPedidoDTO(2, 20);
        listaDeItens.add(itemParatestesUm);
        listaDeItens.add(itemParatestesDois);
        listaDeItens.add(itemParatestesTres);
        return listaDeItens;
    }
}
