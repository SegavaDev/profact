package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Response implements Serializable {

    /** Mensaje informativo en la respuesta */
    private String mensaje;

    public Response(final String mensaje) {
        this.mensaje = mensaje;
    }
    
}
