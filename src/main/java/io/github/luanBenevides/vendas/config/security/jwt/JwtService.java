package io.github.luanBenevides.vendas.config.security.jwt;

import io.github.luanBenevides.vendas.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value(("${security.jwt.expiracao}"))
    private String expiracao;
    @Value(("${security.jwt.chave-assinatura}"))
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario ) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        //podemos utilizar os claims .setClaims()
        return Jwts
                .builder()
                .setSubject(usuario.getLogin()) //identifica usuario
                .setExpiration(data) //define a data de expiração
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims( String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido( String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
            dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuarioPeloToken(String token ) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }
}
