package br.com.challenge.forum.forumhub_oficial.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(Long id, @NotBlank String nome, @NotBlank @Email String email, @NotBlank String password, String perfis) {

}
    