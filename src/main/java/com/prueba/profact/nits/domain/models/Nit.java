package com.prueba.profact.nits.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Nit {

  /**
   * Id del nit
   */
  private long nitId;

  /**
   * Documento del nit
   */
  private String nitDoc;

  /**
   * Nombre del nit
   */
  private String nitNom;

  /**
   * Cupo del nit
   */
  private double nitCupo;

  /**
   * Número de días de plazo del nit
   */
  private int nitPlazo;

  /**
   * Cartera o deuda del nit
   */
  private double nitCartera;

  /**
   * Valida si el nit tiene cupo disponible
   * 
   * @param cartera deuda actual o por adquirir
   * @return true si la deuda no supera el cupo
   */
  public boolean tieneCupo(final double cartera) {
    return cartera <= this.nitCupo;
  }

}
