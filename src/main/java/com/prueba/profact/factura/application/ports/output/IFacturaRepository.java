package com.prueba.profact.factura.application.ports.output;

import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.FacturaException;

/**
 * Interfaz con métodos de persistencia disponibles para factura
 */
public interface IFacturaRepository {

  /**
   * Busca una factura por su id
   * @param id id de la factura
   * @return la factura
 * @throws FacturaException 
    */
  Factura buscFacturaPorId(final long id) throws FacturaException;

  /**
   * Busca una factura por el su consecutivo
   * @param numero consecutivo
   * @return a factural
 * @throws FacturaException 
    */
  Factura buscFacturaPorNumero(final String numero) throws FacturaException;

  /**
   * Genera una factura y envía a persistir
   * @param factura factura a guardar
   * @return la factura fue guardada
 * @throws FacturaException 
    */
  Factura generarFactura(final Factura factura) throws FacturaException;
  
}
