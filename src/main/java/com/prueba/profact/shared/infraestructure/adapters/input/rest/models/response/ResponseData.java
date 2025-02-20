package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResponseData extends Response {

    /** Datos en la respuesta */
    private Object data;

    public ResponseData(final String mensaje, final Object data) {
        super(mensaje);
        this.data = data;
    }
    
}
