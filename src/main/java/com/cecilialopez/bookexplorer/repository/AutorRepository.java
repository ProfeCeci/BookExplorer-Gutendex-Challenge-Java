package com.cecilialopez.bookexplorer.repository;

import com.cecilialopez.bookexplorer.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Nuevo método para buscar un autor por su nombre y evitar duplicados
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte >= :anio)")
    List<Autor> findAutoresVivosEnAnio(Integer anio);
}
