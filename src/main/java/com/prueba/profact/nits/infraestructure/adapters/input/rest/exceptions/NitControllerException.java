package com.prueba.profact.nits.infraestructure.adapters.input.rest.exceptions;

/**
 * Clase encargada de manejar las excepciones en el controlador
  */
public class NitControllerException extends Exception {

    public NitControllerException(final String mensaje) {
        super(mensaje);
    }

    public NitControllerException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
