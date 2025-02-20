package com.prueba.profact.factura.application.ports.input;

import com.prueba.profact.factura.application.exceptions.SharedFacturaServicesException;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;

/**
 * Interfaz para conectar servicios compartidos con slice factura
 */
public interface ISharedFacturaServices {

  /**
   * Interfaz para conectar desde un controlador externo al slice de factura
   * @param sharedFacturaDTO factura compartida a guardar
   * @return una copia de la factura guardada
 * @throws SharedFacturaServicesException 
    */
  SharedFacturaDTO generarFactura(final SharedFacturaDTO sharedFacturaDTO) throws SharedFacturaServicesException;

}
