package com.prueba.profact.nits.application.exceptions;

public class NitNoCupoException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
      public NitNoCupoException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public NitNoCupoException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
