package com.prueba.profact.factura.infraestructure.adapters.input.rest.exceptions;

/**
 * Clase encargada de manejar las excepciones en el controlador
  */
public class FacturaControllerException extends Exception {

    public FacturaControllerException(final String mensaje) {
        super(mensaje);
    }

    public FacturaControllerException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
