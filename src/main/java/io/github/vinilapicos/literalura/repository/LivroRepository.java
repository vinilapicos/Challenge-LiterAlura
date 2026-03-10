package io.github.vinilapicos.literalura.repository;

import io.github.vinilapicos.literalura.model.Idioma;
import io.github.vinilapicos.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);

    List<Livro> findByIdioma(Idioma idioma);
}