package br.com.challenge.forum.forumhub_oficial.model.topicos;

import jakarta.validation.constraints.NotBlank;



public record TopicosDTO(
    @NotBlank
    String titulo,

    @NotBlank
    String mensagem,

    @NotBlank
    String nomeCurso
) {}
