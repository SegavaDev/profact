package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SharedFacturaDTO implements Serializable {

  /**
   * Número de la factura
   */
  private long factNum;

  /**
   * Id o llave foránea del nit relacionado
   */
  private String factNitId;

  /**
   * Fecha de emisión de la factura
   */
  private double factFecha;

  /**
   * Fecha de vencimiento de la factura
   */
  private LocalDate factFechaVenc;

  /**
   * Total de la venta (suma de todos los artículos)
   */
  private double factTotalVent;

  /**
   * Total de costos (suma de todos los artículos)
   */
  private double factTotalCost;

}
