package com.prueba.profact.factura.infraestructure.adapters.input.rest.models;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FacturaDTO implements Serializable {

  /**
   * Número de la factura
   */
  private String factNum;

  /**
   * Id o llave foránea del nit relacionado
   */
  private String factNitId;

  /**
   * Fecha de emisión de la factura
   */
  private LocalDate factFecha;

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
