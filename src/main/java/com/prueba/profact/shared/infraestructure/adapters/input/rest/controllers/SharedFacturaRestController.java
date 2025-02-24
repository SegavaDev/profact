package com.prueba.profact.shared.infraestructure.adapters.input.rest.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.profact.factura.application.ports.input.ISharedFacturaServices;
import com.prueba.profact.kardex_factura.application.ports.input.ISharedKardexFacturaServices;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums.MensajesRespuesta;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.TransaccionFactura;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response.ResponseData;
import com.prueba.profact.shared.utils.errors_manager.ErrorMessageManager;
import com.prueba.profact.shared.utils.errors_manager.ErroresBindingResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/factura")
public class SharedFacturaRestController {

    /** Adaptador de Servicios para factura */
    private final ISharedFacturaServices SHARED_FACTURA_SERVICES;
    /** Adaptador de servicios para kardex factura*/
    private final ISharedKardexFacturaServices SHARED_KARDEX_SERVICES;

    /** Claves de mensajes de respuestas en captura de errores */
    private final String MENSAJE = "mensaje";
    private final String STATUS = "status";

    /**
     * Genera una factura
     * 
     * @param documento documento a buscar
     * @param result    errores almacenados durante la validación de la solicitud
     * @return
     */
    @PostMapping("/generar-factura")
    public ResponseEntity<ResponseData> generarFactura(@Valid @RequestBody TransaccionFactura transaccionFactura, BindingResult result) {

        ResponseData responseData = new ResponseData();
        System.out.println(transaccionFactura.toString());
        if (result.hasErrors()) {
            List<String> errors = ErroresBindingResult.recuperarErrores(result);

            responseData.setMensaje(MensajesError.CLIENTE.lanzar());
            responseData.setData(errors);

            return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            /** 
             * Se guarda la factura y se retorna para obtener su id
             */
            SharedFacturaDTO sharedFacturaDTO = this.SHARED_FACTURA_SERVICES.generarFactura(transaccionFactura.getFacturaDTO());
            /** Asigna el id de la factura a los kardex */
            transaccionFactura.getListKardexFacturaDTO().stream().forEach(
                k -> k.setKrdxFactId(sharedFacturaDTO.getFactId())
            );

            boolean sharedKardexDTO = this.SHARED_KARDEX_SERVICES.guardarListaKardex(transaccionFactura.getListKardexFacturaDTO());

            if(!sharedKardexDTO) {
                System.out.println("Error al registrar los kardex");
            }
            if(sharedFacturaDTO == null) {
                System.out.println("Error en el registro de la factura");
            }

            responseData.setData(true);
            responseData.setMensaje(MensajesRespuesta.REGISTRO_FACTURA_OK.responder());

        } catch (Exception e) {
            System.out.println("Mensaje de error SharedFacturaRestController/generarFactura: " + e);
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