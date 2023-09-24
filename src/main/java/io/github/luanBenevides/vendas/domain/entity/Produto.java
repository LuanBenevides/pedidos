package io.github.luanBenevides.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 100)
    @NotEmpty(message = "{campo.produto-descricao.obrigatorio}")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "{campo.produto-valor-unitario.obrigatorio}")
    private BigDecimal precoUnitario;
}
