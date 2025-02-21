package com.prueba.profact.factura.infraestructure.adapters.input.rest.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.profact.factura.application.ports.input.IFacturaServices;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums.MensajesRespuesta;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response.ResponseData;
import com.prueba.profact.shared.utils.errors_manager.ErrorMessageManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/factura")
public class FacturaRestController {

    /** Servicios para factura */
    private final IFacturaServices FACTURA_SERVICES;

    /** Claves de mensajes de respuestas en captura de errores */
    private final String MENSAJE = "mensaje";
    private final String STATUS = "status";

    /**
     * Genera un consecutivo para factura
     * 
     * @return una entidad con el consecutivo generado o encontrado
     */
    @GetMapping("/generar-consecutivo")
    public ResponseEntity<ResponseData> generarConsecutivoFactura() {

        ResponseData responseData = new ResponseData();

        try {
            responseData.setData(this.FACTURA_SERVICES.generarConsecutivo().getConsFactNum());
            responseData.setMensaje(MensajesRespuesta.CONSECUTIVO_OK.responder());

        } catch (Exception e) {
            System.out.println("Mensaje de error FacturaRestController/generarConsecutivoFactura: " + e);
            final String VALOR = e.getClass().getSimpleName();

            Map<String, Object> resp = ErrorMessageManager.existe(VALOR)
                    ? ErrorMessageManager.valueOf(VALOR).getMensaje()
                    : null;

            final String MENSAJE = resp != null
                    ? resp.get(this.MENSAJE).toString()
                    : MensajesError.INTERNO.lanzar();
            final HttpStatus STATUS = resp != null
                    ? (HttpStatus) resp.get(this.STATUS)
                    : HttpStatus.INTERNAL_SERVER_ERROR;

            responseData.setMensaje(MENSAJE);
            responseData.setData(null);
            return new ResponseEntity<ResponseData>(responseData, STATUS);
        }

        return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);

    }

}