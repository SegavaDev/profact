package com.prueba.profact.factura.application.ports.output;

import java.util.Optional;

import com.prueba.profact.factura.domain.models.ConsecutivoFactura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.ConsecutivoFacturaException;

/**
 * Interfaz con métodos de persistencia disponibles para los consecutivos de facturas
 */
public interface IConsFacturaRepository {

  /**
   * Genera o recupera un consecutivo que aun no esté activo 
   * @return una clase consecutivo de factura
 * @throws ConsecutivoFacturaException 
   */
  Optional<ConsecutivoFactura> generarConsecutivo() throws ConsecutivoFacturaException;

  /**
   * Asigna un consecutivo a una factura ya creada
   * @param factId id de la factura a quien se asigna el consecutivo
   * @param factNum número de la factura o consecutivo
   * @return true si fue asignado con éxito
 * @throws ConsecutivoFacturaException 
    */
  boolean asignarConsecutivo(final long factId, final String factNum) throws ConsecutivoFacturaException;
  
}
