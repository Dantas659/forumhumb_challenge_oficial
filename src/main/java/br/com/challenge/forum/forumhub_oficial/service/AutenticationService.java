package br.com.challenge.forum.forumhub_oficial.service;

import org.springframework.stereotype.Service;

import br.com.challenge.forum.forumhub_oficial.model.usuario.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return usuarioRepository.findByEmail(email);
	}
}
