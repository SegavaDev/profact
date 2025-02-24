package com.prueba.profact.articulos.application.ports.input;

import java.util.List;

import com.prueba.profact.articulos.application.exceptions.SharedArticuloServicesException;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedKardexFacturaDTO;

/**
 * Interfaz con servicios compartidos disponibles para artículos
 */
public interface ISharedArticuloServices {

  /**
   * Actualiza el saldo en inventario de un artículo
   * 
   * @param articulosKardex  los artículos dentro de los kardex que se van a afectar
   * @return true si fue posible actualizar el saldo
   * @throws SharedArticuloServicesException 
   */
  boolean actualizarSaldo(final List<SharedKardexFacturaDTO> articulosKardex) throws SharedArticuloServicesException;

}
