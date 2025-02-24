package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SharedKardexFacturaDTO implements Serializable {

  /**
   * Id o llave foránea del artículo relacionado
   */
  private long krdxArtId;

  /**
   * Id o llave foránea del artículo relacionado
   */
  private long krdxFactId;

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
  private int krdxUnds;

  /** Total de la venta */
  private double krdxTotalVnta;

  /** 
   * Total de los costos
   */
  private double krdxTotalCostos;

}
