package com.prueba.profact.factura.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ConsecutivoFactura {

  /** Id del consecutivo factura */
  private long consFactId;

  /**
   * Id o llave foránea de la factura relacionada
   */
  private long factId;

  /**
   * Número de la factura
   */
  private String consFactNum;

  /** Indicador de si el consecutivo fue activado/asignado */
  private boolean consFactActivo;

  public ConsecutivoFactura(final String factNum) {
    this.consFactNum = factNum;
    this.consFactActivo = false;
  }

}
