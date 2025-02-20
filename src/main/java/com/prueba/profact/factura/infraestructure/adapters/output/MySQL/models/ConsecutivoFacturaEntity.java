package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "consecutivos_facturas")
@Table(name = "consecutivos_facturas")
public class ConsecutivoFacturaEntity {

  /** Id del consecutivo factura */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long consFactId;

  /**
   * Id o llave foránea de la factura relacionada
   */
  private long factId;

  /**
   * Número de la factura
   */
  @Column(nullable = false)
  private String consFactNum;

  /** Indicador de si el consecutivo fue activado/asignado */
  @Column(nullable = false, columnDefinition = "boolean default false")
  private boolean consFactActivo;

}
