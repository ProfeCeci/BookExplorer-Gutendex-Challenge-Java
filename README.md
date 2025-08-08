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
