package io.github.luanBenevides.vendas.rest.controller;

import io.github.luanBenevides.vendas.domain.entity.Usuario;
import io.github.luanBenevides.vendas.rest.dto.UsuarioDTO;
import io.github.luanBenevides.vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl service;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return service.salvar(usuario);
    }
}
