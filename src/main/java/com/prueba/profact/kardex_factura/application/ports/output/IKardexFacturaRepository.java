package com.prueba.profact.kardex_factura.application.ports.output;

import java.util.List;

import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.exceptions.KardexFacturaException;

/**
 * Interfaz con métodos de persistencia disponibles para factura
 */
public interface IKardexFacturaRepository {

  /**
   * Guarda una lista de kardex
   * @param listkardexFactura lista con los kardex a persistir
   * @return true si fue exitoso el proceso
 * @throws KardexFacturaException 
   */
  boolean guardarKardex(final List<KardexFactura> listkardexFactura) throws KardexFacturaException;
  
}
