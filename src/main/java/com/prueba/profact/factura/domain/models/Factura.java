package com.prueba.profact.factura.domain.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Factura {

  /**
   * Id de la factura
   */
  private long factId;

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
   * Total de la venta (suma de todos los artículos con naturaleza negativa)
   */
  private double factTotalVent;

  /**
   * Total de costos (suma de todos los artículos)
   */
  private double factTotalCost;

  /** Ids de kardex o detalles de la factura */
  private List<Long> krdxFactIds;

}
