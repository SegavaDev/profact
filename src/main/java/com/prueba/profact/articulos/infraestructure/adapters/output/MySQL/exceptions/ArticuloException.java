package com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.exceptions;

/**
 * Excepción para manejar los errores de persistencia
  */
public class ArticuloException extends Exception {

    /**
     * Lanza un mensaje personalizado 
     * @param mensaje
      */
    public ArticuloException(final String mensaje) {
        super(mensaje);
    }

    /**
     * Lanza un mensaje personalizado junto con un código para identificarlo
     * @param mensaje
     * @param codigo
      */
    public ArticuloException(final String mensaje, final Throwable codigo) {
        super(mensaje, codigo);
    }
    
}
