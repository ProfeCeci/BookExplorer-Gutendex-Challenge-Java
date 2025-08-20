# Book Explorer - Desafío Alura

Este proyecto es parte del desafío del curso "Java: trabajando con lambdas, streams y Spring Framework" de Alura Latam. La aplicación, **Book Explorer**, consume la API de [Gutendex](https://gutendex.com/) para buscar y mostrar información sobre libros.

El objetivo principal es poner en práctica el consumo de APIs, el tratamiento de datos con Streams y Lambdas, y la conversión de datos JSON a objetos Java.

## Funcionalidades Actuales

Por el momento, la interacción se realiza directamente a través de la consola al ejecutar la aplicación.

*   **Búsqueda de libros por título:** Permite buscar un libro específico en la API por su título.
*   **Top 10 Libros:** Muestra una lista de los 10 libros más descargados.
*   **Estadísticas de Descargas:** Calcula y muestra la media, el máximo y el mínimo de descargas de los libros consultados.

## Próximas Funcionalidades (En Desarrollo)

Se planea implementar las siguientes mejoras:

*   Un menú interactivo para que el usuario elija la opción deseada.
*   Búsqueda de libros por autor.
*   Listar todos los autores registrados.
*   Listar autores vivos en un determinado año.
*   Listar libros por idioma.

## Tecnologías Utilizadas

*   **Java 21**
*   **Spring Framework** (para el consumo de la API)
*   **Jackson** (para la deserialización de JSON)

## Cómo Empezar

1.  Clona este repositorio.
2.  Abre el proyecto en tu IDE de preferencia (e.g., IntelliJ IDEA).
3.  Ejecuta la clase ` BookexplorerApplication.java` para iniciar la aplicación.

---

## Actualización - Desafío LiterAlura

Esta nueva versión de **Book Explorer** ha sido actualizada para cumplir con los requisitos del desafío **LiterAlura**. La aplicación ahora se conecta a una base de datos PostgreSQL y ofrece un menú interactivo con funcionalidades más avanzadas.

### Cambios y Nuevas Funcionalidades

*   **Persistencia de Datos:** Se ha integrado una base de datos **PostgreSQL** utilizando **Spring Data JPA** para almacenar y gestionar la información de libros y autores.
*   **Menú Interactivo:** La aplicación ahora cuenta con un menú en la consola que permite al usuario elegir entre las siguientes opciones:
    1.  **Buscar libro por título:** Busca un libro en la API de Gutendex y lo guarda en la base de datos si no existe.
    2.  **Listar libros registrados:** Muestra todos los libros almacenados en la base de datos.
    3.  **Listar autores registrados:** Muestra todos los autores guardados.
    4.  **Listar autores vivos en un determinado año:** Permite al usuario ingresar un año y muestra los autores que estaban vivos en ese período.
    5.  **Listar libros por idioma:** Muestra los libros registrados en un idioma específico (ES, EN, FR, PT).
*   **Manejo de Duplicados:** Se ha implementado una lógica para evitar que el mismo libro sea registrado más de una vez.
*   **Corrección de Errores:** Se solucionó un error en la búsqueda de libros por idioma que era sensible a mayúsculas y minúsculas.

### Tecnologías Actualizadas

*   **Java 17**
*   **Spring Data JPA**
*   **PostgreSQL Driver**
