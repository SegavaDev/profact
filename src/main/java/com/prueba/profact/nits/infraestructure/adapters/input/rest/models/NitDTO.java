package com.prueba.profact.nits.infraestructure.adapters.input.rest.models;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class NitDTO implements Serializable {

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
   * Fecha plazo del nit
   */
  private LocalDate nitPlazo;

  /**
   * Cartera o deuda del nit
   */
  private double nitCartera;

}
