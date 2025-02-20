package com.prueba.profact.factura.application.exceptions;

/**
 * Excepción para manejar los errores de servicios
  */
public class NoCreateConsecutivoFactException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public NoCreateConsecutivoFactException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public NoCreateConsecutivoFactException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
