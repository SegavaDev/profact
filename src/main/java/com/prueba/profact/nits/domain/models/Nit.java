package com.prueba.profact.nits.domain.models;

import java.time.LocalDate;
import java.time.Period;

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
   * Fecha plazo del nit
   */
  private LocalDate nitPlazo;

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

  /**
   * Valida si tiene mínimo 1 día de plazo para el pago de cartera.
   * El calculo se hace bajo restricción de la fecha actual.
   * 
   * @param fecha fecha en la que se adquiere la deuda a evaluar
   * @return
   */
  public boolean hayPlazo(final LocalDate fecha) {
    if (fecha != LocalDate.now()) {
      return Period.between(LocalDate.now(), this.nitPlazo).getDays() >= 1;
    } else {
      return Period.between(fecha, this.nitPlazo).getDays() >= 1;
    }
  }

}
