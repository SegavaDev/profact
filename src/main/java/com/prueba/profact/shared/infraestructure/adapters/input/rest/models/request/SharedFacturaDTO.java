package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SharedFacturaDTO implements Serializable {

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
  private long factNitId;

  /**
   * Documento del cliente
    */
  private String factNitDoc;

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
