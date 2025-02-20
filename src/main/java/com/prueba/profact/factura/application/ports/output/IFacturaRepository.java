package com.prueba.profact.factura.application.ports.output;

import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.FacturaException;

/**
 * Interfaz con métodos de persistencia disponibles para factura
 */
public interface IFacturaRepository {

  /**
   * Genera una factura y envía a persistir
   * @param factura factura a guardar
   * @return la factura guardada
 * @throws FacturaException 
    */
  boolean generarFactura(final Factura factura) throws FacturaException;
  
}
