package com.prueba.profact.articulos.infraestructure.adapters.input.rest.exceptions;

/**
 * Clase encargada de manejar las excepciones en el controlador
  */
public class ArticuloControllerException extends Exception {

    public ArticuloControllerException(final String mensaje) {
        super(mensaje);
    }

    public ArticuloControllerException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
