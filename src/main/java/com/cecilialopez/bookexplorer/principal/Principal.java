package com.cecilialopez.bookexplorer.principal;

import com.cecilialopez.bookexplorer.model.*;
import com.cecilialopez.bookexplorer.repository.AutorRepository;
import com.cecilialopez.bookexplorer.repository.LibroRepository;
import com.cecilialopez.bookexplorer.service.ConsumoAPI;
import com.cecilialopez.bookexplorer.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    *** Bienvenido a bookexplorer ***
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine(); // Limpiar buffer
                continue;
            }


            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toLowerCase().contains(tituloLibro.toLowerCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibros datosLibro = libroBuscado.get();

            // Evitar duplicados de libros
            Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro \'" + libroExistente.get().getTitulo() + "\' ya está registrado.");
                return; // Salir del metodo si el libro ya existe
            }

            // --- LÓGICA CORRECTA PARA MANEJAR EL AUTOR ---
            Autor autor;
            if (datosLibro.autor().isEmpty()) {
                System.out.println("Advertencia: La API no proporcionó un autor para este libro. Se registrará sin autor.");
                autor = null; // Permitir libros sin autor si la API no lo da
            } else {
                DatosAutor datosAutor = datosLibro.autor().get(0);
                // Buscar si el autor ya existe en nuestra base de datos
                Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());

                if (autorExistente.isPresent()) {
                    autor = autorExistente.get(); // Usar el autor existente de la BD
                } else {
                    autor = new Autor(datosAutor); // Crear un nuevo autor si no existe
                }
            }

            // Crear y guardar el libro con su autor
            Libro libro = new Libro(datosLibro);
            libro.setAutor(autor); // Establecer la relación
            libroRepository.save(libro); // Guardar el libro (y el autor si es nuevo, por la cascada)

            System.out.println("Libro registrado exitosamente:");
            System.out.println(libro);

        } else {
            System.out.println("Libro no encontrado en la API de Gutendex.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("----- LIBROS REGISTRADOS -----");
            libros.forEach(System.out::println);
            System.out.println("----------------------------");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("----- AUTORES REGISTRADOS -----");
            autores.forEach(System.out::println);
            System.out.println("-----------------------------");
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine(); // Limpiar buffer
            List<Autor> autores = autorRepository.findAutoresVivosEnAnio(anio);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio + ".");
            } else {
                System.out.println("----- AUTORES VIVOS EN " + anio + " -----");
                autores.forEach(System.out::println);
                System.out.println("---------------------------------");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un año válido (número).");
            teclado.nextLine(); // Limpiar buffer
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el código del idioma para buscar libros (ej: ES, EN, FR, PT):");
        var idioma = teclado.nextLine().toLowerCase();
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma \'" + idioma + "\'.");
        } else {
            System.out.println("----- LIBROS EN IDIOMA " + idioma.toUpperCase() + " -----");
            libros.forEach(System.out::println);
            System.out.println("---------------------------------");
        }
    }
}