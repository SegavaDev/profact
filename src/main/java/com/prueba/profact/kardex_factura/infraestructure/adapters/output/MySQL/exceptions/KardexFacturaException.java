package com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.exceptions;

/**
 * Excepción para manejar los errores de persistencia
  */
public class KardexFacturaException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public KardexFacturaException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public KardexFacturaException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
