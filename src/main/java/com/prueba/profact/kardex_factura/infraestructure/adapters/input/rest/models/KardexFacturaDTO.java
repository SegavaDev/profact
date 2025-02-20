package com.prueba.profact.kardex_factura.infraestructure.adapters.input.rest.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class KardexFacturaDTO implements Serializable {

  /**
   * Id del kardex factura
   */
  private long krdxId;

  /**
   * Id o llave foránea de la factura relacionado
   */
  private long krdxFactId;

  /**
   * Id o llave foránea del artículo relacionado
   */
  private long krdxArtId;

  /**
   * Código del artículo
   */
  private String krdxArtCod;

  /**
   * Nombre del artículo
   */
  private String krdxArtNom;

  /**
   * Nombre del laboratorio
   */
  private String krdxArtLab;

  /**
   * Naturaleza del detalle (positivo o negativo)
   */
  private boolean krdxArtNat;

  /** 
   * Unidades adquiridas del artículo
  */
  private long krdxUnds;

  /** Total de la venta */
  private double krdxTotalVnta;

  /** 
   * Total de los costos
   */
  private double krdxTotalCostos;

}
