package com.prueba.profact.articulos.domain.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Articulo {

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

    /**
     * Método para reducir la cantidad de artículos en el inventario
     * @param cantidad cantidad de unidades a reducir
     * @return true si fue posible la reducción, en caso contrario false
      */
    public boolean reducirSaldo(final int cantidad) {
        if(cantidad <= this.artSaldo) {
            this.setArtSaldo(this.artSaldo - cantidad);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Método para aumentar la cantidad de artículos en el inventario
     * @param cantidad cantidad de unidades a aumentar
     * @return true si fue posible el aumento, en caso contrario false
      */
      public void aumentarSaldo(final int cantidad) {
        this.setArtSaldo(this.artSaldo + cantidad);
    }

    /**
     * Valida si una cantidad de unidades es mayor a el saldo del articulo
     * @param cantidad cantidad a validar contra las existentes
     * @return true si es mayor la cantidad, de lo contrario sera false
      */
    public boolean validarSaldo(final int cantidad) {
      return cantidad >= this.artSaldo;
    }
    
}
