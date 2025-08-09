package br.com.challenge.forum.forumhub_oficial.model.cursos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CursosRepository extends JpaRepository<CursosEntity, Long> {

    Optional<CursosEntity> findByNome(String nomeCurso);

}
