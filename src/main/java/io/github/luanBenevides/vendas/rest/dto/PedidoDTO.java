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
    @NotNull(message = "{campo.pedidoDTO-cliente}")
    private Integer cliente;
    @NotNull(message = "{campo.pedidoDTO-total}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.pedidoDTO-itens}")
    private List<ItemPedidoDTO> itens;
}
