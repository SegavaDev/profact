package com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.models;

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
@Entity(name = "kardex_factura")
@Table(name = "kardex_factura")
public class KardexFacturaEntity {

  /**
   * Id del kardex factura
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long krdxId;

  /**
   * Id o llave foránea de la factura relacionado
   */
  @Column(nullable = false)
  private long krdxFactId;

  /**
   * Id o llave foránea del artículo relacionado
   */
  @Column(nullable = false)
  private long krdxArtId;

  /**
   * Código del artículo
   */
  @Column(nullable = false)
  private String krdxArtCod;

  /**
   * Nombre del artículo
   */
  
  @Column(nullable = false)
  private String krdxArtNom;

  /**
   * Nombre del laboratorio
   */
  @Column(nullable = false)
  private String krdxArtLab;

  /**
   * Naturaleza del detalle (positivo o negativo)
   */
  @Column(nullable = false)
  private boolean krdxArtNat;

  /** 
   * Unidades adquiridas del artículo
  */
  @Column(nullable = false)
  private long krdxUnds;

  /** 
   * Total de la venta 
  */
  @Column(nullable = false)
  private double krdxTotalVnta;

  /** 
   * Total de los costos
   */
  @Column(nullable = false)
  private double krdxTotalCostos;

}
