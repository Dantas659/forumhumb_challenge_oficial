package br.com.challenge.forum.forumhub_oficial.model.cursos;

import java.util.ArrayList;
import java.util.List;

import br.com.challenge.forum.forumhub_oficial.model.topicos.TopicoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CursosEntity {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    @Column(name = "cursos_enum", nullable = false)
    private CursosEnum cursoEnum;

    @OneToMany(mappedBy = "cursos")
    private List<TopicoEntity> topicos = new ArrayList<>();

    public CursosEntity(CursosDTO curso) {
            this.nome = curso.nome();
            this.cursoEnum = curso.cursoEnum();
        }
}



