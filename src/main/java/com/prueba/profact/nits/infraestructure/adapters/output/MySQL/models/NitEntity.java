package com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "nits")
@Table(name = "nits")
public class NitEntity {

  /**
   * Id del nit
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long nitId;

  /**
   * Documento del nit
   */
  @Size(min = 10, max = 10, message = "El documento del nit debe contener mínimo 10 dígitos y máximo 10")
  @Column(unique = true, nullable = false)
  private String nitDoc;

  /**
   * Nombre del nit
   */
  @Size(min = 3, message = "El nombre del nit debe contener mínimo 3 caracteres")
  @Column(nullable = false)
  private String nitNom;

  /**
   * Cupo del nit
   */
  @Column(nullable = false, columnDefinition = "double default 0")
  private double nitCupo;

  /**
   * Número de días de plazo del nit
   */
  @Column(nullable = false)
  private int nitPlazo;

  /**
   * Cartera o deuda del nit
   */
  @Column(columnDefinition = "double default 0")
  private double nitCartera;

}
