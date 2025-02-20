package com.prueba.profact.nits.infraestructure.adapters.output.MySQL.exceptions;

/**
 * Excepción para manejar los errores de persistencia
  */
public class NoFoundNitException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public NoFoundNitException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public NoFoundNitException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
