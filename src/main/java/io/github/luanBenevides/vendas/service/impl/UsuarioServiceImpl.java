package io.github.luanBenevides.vendas.service.impl;

import io.github.luanBenevides.vendas.domain.entity.Usuario;
import io.github.luanBenevides.vendas.domain.repository.UsuarioRespository;
import io.github.luanBenevides.vendas.rest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRespository respository;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public UsuarioDTO salvar(Usuario usuario) {
        respository.save(usuario);
        return new UsuarioDTO(usuario.getLogin(), usuario.isAdmin());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = respository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        String[] roles = usuario.isAdmin() ?
                new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
