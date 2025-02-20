package com.prueba.profact.kardex_factura.application.exceptions;

/**
 * Excepción para manejar los errores de los servicios
  */
public class KardexFacturaServicesException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public KardexFacturaServicesException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public KardexFacturaServicesException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
