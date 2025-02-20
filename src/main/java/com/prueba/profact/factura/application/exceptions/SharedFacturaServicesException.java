package com.prueba.profact.factura.application.exceptions;

/**
 * Excepción para manejar los errores de los servicios
  */
public class SharedFacturaServicesException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public SharedFacturaServicesException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public SharedFacturaServicesException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
