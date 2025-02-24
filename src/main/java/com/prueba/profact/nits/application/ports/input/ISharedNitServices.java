package com.prueba.profact.nits.application.ports.input;

import com.prueba.profact.nits.application.exceptions.SharedNitServicesException;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;

/**
 * Interfaz con servicios compartidos disponibles para nit
 */
public interface ISharedNitServices {

  /**
   * Actualiza un nit en la base de datos
   * 
   * @param sharedFacturaDto la factura que contiene la información necesaria para actualizar
   * @return true si fue posible la actualización
 * @throws SharedNitServicesException 
   */
  long actualizar(final SharedFacturaDTO sharedFacturaDto) throws SharedNitServicesException;

}
