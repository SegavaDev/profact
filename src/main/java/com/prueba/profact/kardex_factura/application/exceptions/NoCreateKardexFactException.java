package com.prueba.profact.kardex_factura.application.exceptions;

/**
 * Excepción para manejar los errores de servicios
  */
public class NoCreateKardexFactException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public NoCreateKardexFactException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public NoCreateKardexFactException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
