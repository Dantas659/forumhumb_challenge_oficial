package br.com.challenge.forum.forumhub_oficial.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;


import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioEntity;

@Service
public class TokenService {

    @Value("${api.forumhub.token.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario) {

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(String.valueOf(usuario.getId()))
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token JWT inválido", e);
        }
    }
}

