package br.com.challenge.forum.forumhub_oficial.model.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

     @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrar(UsuarioDTO dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.password());
        UsuarioEntity usuario = new UsuarioEntity(dto.nome(), dto.email(), senhaCriptografada, dto.perfis());
        usuarioRepository.save(usuario);
    }
    
}
