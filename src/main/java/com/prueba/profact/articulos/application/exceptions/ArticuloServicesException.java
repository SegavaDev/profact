package com.prueba.profact.articulos.application.exceptions;

/**
 * Excepción para manejar los errores de persistencia
  */
public class ArticuloServicesException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public ArticuloServicesException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public ArticuloServicesException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
