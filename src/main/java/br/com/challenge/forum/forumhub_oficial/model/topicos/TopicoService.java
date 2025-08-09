package br.com.challenge.forum.forumhub_oficial.model.topicos;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.challenge.forum.forumhub_oficial.model.cursos.CursosRepository;
import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursosRepository cursosRepository;

    public TopicoEntity cadastrar(TopicosDTO json, Long cursosEntityId, Long usuarioEntityId) {
        
        var usuario = usuarioRepository.findById(usuarioEntityId)
                .orElseThrow(() -> new RuntimeException("Usuário " + usuarioEntityId + " não encontrado"));
        var curso = cursosRepository.findById(cursosEntityId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        TopicoEntity novoTopico = new TopicoEntity(json.titulo(), json.mensagem(), LocalDateTime.now(), true, usuario, curso);
        return topicoRepository.save(novoTopico);
        
    }


}
