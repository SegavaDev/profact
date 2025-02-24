package com.prueba.profact.shared.domain.models.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SharedArticuloActSaldoDTO implements Serializable {

  /**
   * Id del artículo
   */
  private long artId;

  /**
   * Código del artículo
   */
  private String artCod;

  /**
   * Saldo a restar o aumentar
   */
  private int unidades;

  /** 
   * Naturaleza de la transacción 
  */
  private boolean naturaleza;

}
