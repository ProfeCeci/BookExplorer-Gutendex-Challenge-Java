package com.cecilialopez.bookexplorer.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
