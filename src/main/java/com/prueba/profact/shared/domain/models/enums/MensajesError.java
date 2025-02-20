package com.prueba.profact.shared.domain.models.enums;

public enum MensajesError {
    
    CLIENTE("Ha ocurrido un error con la solicitud, por favor revise la información enviada y vuelva a intentarlo"),
    INTERNO("Ha ocurrido un error interno"),
    CONSECUTIVO_NO_CREADO("Ha ocurrido un error al crear el consecutivo, por favor comuníquese con soporte"),
    FACTURA_NO_CREADA("Ha ocurrido un error al crear la factura, por favor comuníquese con soporte"),
    CUPO("No cuenta con cupo disponible"),
    PLAZO("El plazo no es suficiente para una nueva deuda"),
    NO_ENCONTRADO("La información solicitada no fue encontrada"),
    NO_ACTUALIZADA("No fue posible actualizar la información");

    private String mensaje;

    private MensajesError(final String mensaje) {
        this.mensaje = mensaje;
    }

    public String lanzar() {
        return this.mensaje;
    }

}
