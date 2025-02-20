package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TransaccionFactura implements Serializable {

    private SharedFacturaDTO facturaDTO;

    private List<SharedKardexFacturaDTO> listKardexFacturaDTO;
    
}
