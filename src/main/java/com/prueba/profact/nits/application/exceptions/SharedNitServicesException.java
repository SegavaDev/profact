package com.prueba.profact.nits.application.exceptions;

/**
 * Excepción para manejar los errores de persistencia
  */
public class SharedNitServicesException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public SharedNitServicesException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public SharedNitServicesException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
