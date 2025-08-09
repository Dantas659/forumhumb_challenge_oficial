package br.com.challenge.forum.forumhub_oficial.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.challenge.forum.forumhub_oficial.infra.security.DadosJWT;
import br.com.challenge.forum.forumhub_oficial.infra.security.TokenService;
import br.com.challenge.forum.forumhub_oficial.model.autenticacao.AutenticacaoDTO;
import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioEntity;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RestController
@RequestMapping("/auth")
public class AutenticacaoContoller {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) throws UsernameNotFoundException {

        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.email(), autenticacaoDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosJWT(tokenJWT));

    }
}
 