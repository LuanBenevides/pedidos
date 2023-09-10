package io.github.luanBenevides.vendas.domain.entity;

import jakarta.persistence.*;
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
    private String descricao;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
}
