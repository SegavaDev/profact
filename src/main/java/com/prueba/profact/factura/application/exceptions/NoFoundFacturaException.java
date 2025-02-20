package com.prueba.profact.factura.application.exceptions;

/**
 * Excepción para manejar los errores de servicios
  */
public class NoFoundFacturaException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public NoFoundFacturaException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public NoFoundFacturaException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
