package com.prueba.profact.articulos.application.exceptions;

/**
 * Excepción para manejar los errores de persistencia
 */
public class SharedArticuloServicesException extends Exception {

  /**
   * Lanza un mensaje personalizado
   * 
   * @param mensaje
   */
  public SharedArticuloServicesException(final String mensaje) {
    super(mensaje);
  }

  /**
   * Lanza un mensaje personalizado junto con un código para identificarlo
   * 
   * @param mensaje
   * @param codigo
   */
  public SharedArticuloServicesException(final String mensaje, final Throwable codigo) {
    super(mensaje, codigo);
  }

}
