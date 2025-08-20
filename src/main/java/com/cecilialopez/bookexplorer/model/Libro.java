package com.cecilialopez.bookexplorer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numeroDeDescargas;

    // La cascada PERSIST asegura que si guardamos un libro con un autor nuevo,
    // el autor también se guarde en su propia tabla.
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idioma = datosLibros.idiomas().isEmpty() ? "N/A" : datosLibros.idiomas().get(0);
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    // Método clave para establecer la relación entre Libro y Autor
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        // Comprobación para evitar errores si el autor es nulo
        String nombreAutor = (autor != null) ? autor.getNombre() : "Autor desconocido";
        return "----- LIBRO -----\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + nombreAutor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "-----------------";
    }
}
