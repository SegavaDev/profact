package com.prueba.profact.kardex_factura.application.ports.input;

import java.util.List;

import com.prueba.profact.kardex_factura.application.exceptions.KardexFacturaServicesException;
import com.prueba.profact.kardex_factura.domain.models.KardexFactura;

/**
 * Interfaz con servicios disponibles
 */
public interface IKardexFacturaServices {

  /**
   * Guarda una lista de kardex 
   * @param listkardexFactura lista de kardex
   * @return true si fue exitoso el proceso
   * @throws KardexFacturaServicesException
    */
  boolean guardarKardex(final List<KardexFactura> listkardexFactura) throws KardexFacturaServicesException;

}
