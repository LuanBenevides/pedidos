package io.github.luanBenevides.vendas.domain.repository;

import io.github.luanBenevides.vendas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
