package io.github.luanBenevides.vendas.rest.dto;

import io.github.luanBenevides.vendas.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;
    @NotNull(message = "O total do pedido é um campo obrigatório")
    private BigDecimal total;
    @NotEmptyList(message = "O pedido não pode ser realizado em inserir ao menos um item.")
    private List<ItemPedidoDTO> itens;
}
