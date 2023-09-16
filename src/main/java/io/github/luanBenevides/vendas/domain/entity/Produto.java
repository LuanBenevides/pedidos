package io.github.luanBenevides.vendas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotEmpty(message = "O campo de descrição é obrigatório.")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "O preço unitário não pode estar vazio.")
    private BigDecimal precoUnitario;
}
