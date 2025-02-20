package com.prueba.profact.shared.domain.models.dtos;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
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
     * Saldo o existencias en inventario
      */
    private int artSaldo;
    
}
