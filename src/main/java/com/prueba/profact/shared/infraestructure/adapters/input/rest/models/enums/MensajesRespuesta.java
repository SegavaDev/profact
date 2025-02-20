package com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums;

/**
 * Mensajes usados para dar respuesta a solicitudes en infraestructura
 */
public enum MensajesRespuesta {
    
    ENCONTRADA("Información encontrada con éxito"),
    NO_ENCONTRADA("Datos incorrectos, no se logró obtener la información"),
    CONSECUTIVO_OK("Se ha creado el consecutivo exitosamente"),
    CONSECUTIVO_NO_CREADO("No se ha podido crear el consecutivo, por favor comuníquese con soporte"),
    REGISTRO_FACTURA_OK("Se ha registrado la factura exitosamente");

    private final String respuesta;

    private MensajesRespuesta(final String respuesta) {
        this.respuesta = respuesta;
    }

    public String responder() {
        return this.respuesta;
    }

}
