package com.cecilialopez.bookexplorer.repository;

import com.cecilialopez.bookexplorer.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Método para evitar registrar un libro existente
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    // Método para listar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
