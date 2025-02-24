package com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NitActCarteraDto {
    /**
     * Documento del cliente
     */
    private String nitDoc;
    /**
     * Cartera o deuda del nit
     */
    private double nitCartera;
}
