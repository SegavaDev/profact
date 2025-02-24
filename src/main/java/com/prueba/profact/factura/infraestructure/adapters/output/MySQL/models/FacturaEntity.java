package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models;

import java.time.LocalDate;

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
@Entity(name = "facturas")
@Table(name = "facturas")
public class FacturaEntity {

  /**
   * Id de la factura
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long factId;

  /**
   * Número de la factura
   */
  @Column(unique = true, nullable = false)
  private String factNum;

  /**
   * Id o llave foránea del nit relacionado
   */
  private long factNitId;

  /**
   * Fecha de emisión de la factura
   */
  @Column(nullable = false)
  private LocalDate factFecha;

  /**
   * Fecha de vencimiento de la factura
   */
  @Column(nullable = false)
  private LocalDate factFechaVenc;

  /**
   * Total de la venta (suma de todos los artículos)
   */
  @Column(nullable = false)
  private double factTotalVent;

  /**
   * Total de costos (suma de todos los artículos)
   */
  @Column(nullable = false)
  private double factTotalCost;
}
