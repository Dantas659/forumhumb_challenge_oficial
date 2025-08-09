package br.com.challenge.forum.forumhub_oficial.controller;


import java.time.LocalDateTime;


import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.challenge.forum.forumhub_oficial.model.cursos.CursosEntity;
import br.com.challenge.forum.forumhub_oficial.model.cursos.CursosRepository;
import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicoEntity;
import br.com.challenge.forum.forumhub_oficial.model.topicos.ListagemTopicoDTO;
import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicoRepository;
import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicoService;
import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicosAtualizacaoDTO;
import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicosDTO;
import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class ResquestController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<Void> registrarTopico(@RequestBody @Valid TopicosDTO json) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuarioEntity = (UsuarioEntity) authentication.getPrincipal();
        System.out.println("Principal: " + usuarioEntity.getClass());
        System.out.println("Principal value: " + usuarioEntity);

        CursosEntity cursosEntity = cursosRepository.findByNome(json.nomeCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        topicoService.cadastrar(json, usuarioEntity.getId(), cursosEntity.getId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/listar")
    public Page<ListagemTopicoDTO> listarTopicos(@PageableDefault(size = 10, sort={"dataCriacao"}) Pageable pageable) {
        return topicoRepository.findAllByEstadoTopicoTrue(pageable).map(ListagemTopicoDTO::new);
    }

    @GetMapping("/listar/{id}")
    public ListagemTopicoDTO listarTopicoPorId(@PathVariable Long id) {
        if(id != null && id >= 0) {
            var topico = topicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        return new ListagemTopicoDTO(topico);
        }

        throw new IllegalArgumentException("ID inválido");
    }

    @PutMapping("atualizar/{id}")
    @Transactional
    public void atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicosAtualizacaoDTO json) {
        var topicos = topicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topicos.atualizarTopico(json);
    }
    
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public void excluirTopico(@PathVariable Long id) {
        var topicos = topicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topicos.inativarTopico();
    }

}
