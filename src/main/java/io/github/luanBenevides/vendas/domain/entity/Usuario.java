package io.github.luanBenevides.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotEmpty(message = "{campo.usuario-login.obrigatorio}")
    private String login;
    @Column
    @NotEmpty(message = "{campo.usuario-senha.obrigatorio}")
    private String senha;
    @Column
    private boolean admin;
}
