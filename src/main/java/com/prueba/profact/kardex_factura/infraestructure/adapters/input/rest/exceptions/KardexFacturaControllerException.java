package com.prueba.profact.kardex_factura.infraestructure.adapters.input.rest.exceptions;

/**
 * Clase encargada de manejar las excepciones en el controlador
  */
public class KardexFacturaControllerException extends Exception {

    public KardexFacturaControllerException(final String mensaje) {
        super(mensaje);
    }

    public KardexFacturaControllerException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
