package io.github.luanBenevides.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "{campo.cliente-nome.obrigatorio}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cliente-cpf.obrigatorio}")
    @CPF(message = "{campo.cliente-cpf.invalido}")
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;
}
