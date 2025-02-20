package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResponseDataList extends Response {

    /** Datos en la respuesta */
    private List<Object> data;

    public ResponseDataList(final String mensaje, final List<Object> data) {
        super(mensaje);
        this.data = data;
    }
    
}
