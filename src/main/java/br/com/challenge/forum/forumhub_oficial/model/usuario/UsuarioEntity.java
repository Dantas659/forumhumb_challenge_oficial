package br.com.challenge.forum.forumhub_oficial.model.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;
    private String perfis;

    @OneToMany(mappedBy = "usuario")
    private List<TopicoEntity> topicos = new ArrayList<>();

    public UsuarioEntity(UsuarioDTO usuario) {
        this.nome = usuario.nome();
        this.email = usuario.email();
        this.password = usuario.password();
        this.perfis = usuario.perfis();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public UsuarioEntity(String nome2, String email2, String senhaCriptografada, String perfis2) {
        this.nome = nome2;
        this.email = email2;
        this.password = senhaCriptografada;
        this.perfis = perfis2;
    }

    
}
