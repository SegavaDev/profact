package com.prueba.profact.factura.application.ports.input;

import com.prueba.profact.factura.domain.models.ConsecutivoFactura;
import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.ConsecutivoFacturaException;

/**
 * Interfaz con servicios disponibles de factura
 */
public interface IFacturaServices {

  /**
   * Genera o recupera un consecutivo que aun no esté activo 
   * @return una clase consecutivo de factura
 * @throws ConsecutivoFacturaException 
   */
  ConsecutivoFactura generarConsecutivo() throws ConsecutivoFacturaException;

  /**
   * Asigna un consecutivo a una factura ya creada
   * @param factId id de la factura a quien se asigna el consecutivo
   * @param factNum número de la factura o consecutivo
   * @return true si fue asignado con éxito
 * @throws ConsecutivoFacturaException 
    */
  boolean asignarConsecutivo(final long factId, final String factNum) throws ConsecutivoFacturaException;

  /**
   * Genera una factura y envía a persistir
   * @param factura factura a guardar
   * @return la factura guardada
 * @throws ConsecutivoFacturaException 
    */
  Factura generarFactura(final Factura factura) throws ConsecutivoFacturaException;

}
