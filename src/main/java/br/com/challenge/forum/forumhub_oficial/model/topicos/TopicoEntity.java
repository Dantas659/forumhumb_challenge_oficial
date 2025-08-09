package br.com.challenge.forum.forumhub_oficial.model.topicos;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.challenge.forum.forumhub_oficial.model.cursos.CursosEntity;
import br.com.challenge.forum.forumhub_oficial.model.cursos.CursosRepository;
import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioEntity;
import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicoEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Boolean estadoTopico;

    // Muitos tópicos podem ter 1 autor
    @ManyToOne
    private UsuarioEntity usuario;

    // Muitos tópicos podem estar ligados a 1 curso
    @ManyToOne
    private CursosEntity cursos;


    public void atualizarTopico(TopicosAtualizacaoDTO json) {
        if(json.titulo() != null) {
            this.titulo = json.titulo();
        }
        if(json.mensagem() != null) {
            this.mensagem = json.mensagem();
        }

    }

    public void inativarTopico() {
       this.estadoTopico = false;
    }

    public TopicoEntity(String titulo2, String mensagem2, LocalDateTime now, boolean estadoTopico, UsuarioEntity usuario,
            CursosEntity curso) {
        this.titulo = titulo2;
        this.mensagem = mensagem2;
        this.dataCriacao = now;
        this.estadoTopico = estadoTopico;

        this.usuario = usuario;
        this.cursos = curso;
    }



}
