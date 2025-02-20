package com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.models;

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
@Entity(name = "articulos")
@Table(name = "articulos")
public class ArticuloEntity {

    /**
     * Id del artículo
      */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long artId;

    /**
     * Código del artículo
      */
    @Size(min = 1, message = "El código del artículo debe contener al menos 1 carácter")
    @Column(unique = true, nullable = false)
    private String artCod;

    /**
     * Nombre del artículo
      */
    @Size(min = 3, message = "El nombre del artículo debe contener al menos 3 caracteres")
    @Column(nullable = false)
    private String artNom;

    /**
     * Laboratorio del artículo
      */
    @Size(min = 3, message = "El laboratorio del artículo debe contener al menos 3 caracteres")
    @Column(nullable = false)
    private String artLab;

    /**
     * Saldo o existencias en inventario
      */
    @Column(nullable = false)
    private int artSaldo;

    /**
     * Costo de producción / adquisición / transporte del artículo
      */
    @Column(nullable = false)
    private double artCosto;

    /**
     * Precio de venta del artículo
      */
    @Column(nullable = false)
    private double artPrecVenta;
    
}
