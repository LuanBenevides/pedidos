package io.github.luanBenevides.vendas.rest.controller;

import io.github.luanBenevides.vendas.config.security.jwt.JwtService;
import io.github.luanBenevides.vendas.domain.entity.Usuario;
import io.github.luanBenevides.vendas.exception.SenhaInvalidarException;
import io.github.luanBenevides.vendas.rest.dto.CredenciaisDTO;
import io.github.luanBenevides.vendas.rest.dto.TokenDTO;
import io.github.luanBenevides.vendas.rest.dto.UsuarioDTO;
import io.github.luanBenevides.vendas.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioServiceImpl service;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return service.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticarUsuario(@RequestBody CredenciaisDTO credenciaisDTO) {
        try {
           Usuario usuario = Usuario.builder()
                   .login(credenciaisDTO.getLogin())
                   .senha(credenciaisDTO.getSenha()).build();
           UserDetails usuarioAutenticado = service.autenticar(usuario);
           String token = jwtService.gerarToken(usuario);
           return new TokenDTO(usuario.getLogin(), token);

        }catch (UsernameNotFoundException | SenhaInvalidarException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
