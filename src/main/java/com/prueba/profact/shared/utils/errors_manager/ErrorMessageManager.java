package com.prueba.profact.shared.utils.errors_manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums.MensajesRespuesta;

public enum ErrorMessageManager {

    NoFoundArticuloException(MensajesRespuesta.NO_ENCONTRADA.responder(), HttpStatus.BAD_REQUEST),
    NoFoundNitException(MensajesRespuesta.NO_ENCONTRADA.responder(), HttpStatus.BAD_REQUEST),
    NitNoCupoException(MensajesError.CUPO.lanzar(), HttpStatus.BAD_REQUEST),
    NitNoPlazoException(MensajesError.PLAZO.lanzar(), HttpStatus.BAD_REQUEST);

    private String mensaje;
    private HttpStatus status;

    private ErrorMessageManager(final String mensaje, final HttpStatus status) {
        this.mensaje = mensaje;
        this.status = status;
    }

    public Map<String, Object> getMensaje() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", this.mensaje);
        resp.put("status", this.status);

        return resp;
    }

    public static boolean existe(final String valor) {
        return Arrays.stream(
                ErrorMessageManager.values())
                .anyMatch(
                        m -> m.name().equals(valor));
    }

}
