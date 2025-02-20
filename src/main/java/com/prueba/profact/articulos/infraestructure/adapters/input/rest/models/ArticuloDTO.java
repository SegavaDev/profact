package com.prueba.profact.articulos.infraestructure.adapters.input.rest.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ArticuloDTO implements Serializable {

    /**
     * Id del artículo
      */
    private long artId;

    /**
     * Código del artículo
      */
    private String artCod;

    /**
     * Nombre del artículo
      */
    private String artNom;

    /**
     * Laboratorio del artículo
      */
    private String artLab;

    /**
     * Saldo o existencias en inventario
      */
    private int artSaldo;

    /**
     * Costo de producción / adquisición / transporte del artículo
      */
    private double artCosto;

    /**
     * Precio de venta del artículo
      */
    private double artPrecVenta;
    
}
