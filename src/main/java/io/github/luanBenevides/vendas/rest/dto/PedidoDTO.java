package io.github.luanBenevides.vendas.rest.dto;

import io.github.luanBenevides.vendas.validation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
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
