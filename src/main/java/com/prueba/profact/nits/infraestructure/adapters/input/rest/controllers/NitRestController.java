package com.prueba.profact.nits.infraestructure.adapters.input.rest.controllers;

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

import com.prueba.profact.nits.application.ports.input.INitServices;
import com.prueba.profact.nits.infraestructure.adapters.input.rest.exceptions.NitControllerException;
import com.prueba.profact.nits.infraestructure.adapters.input.rest.mappers.NitControllerMapper;
import com.prueba.profact.nits.infraestructure.adapters.input.rest.models.NitDTO;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums.MensajesRespuesta;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response.ResponseData;
import com.prueba.profact.shared.utils.errors_manager.ErrorMessageManager;
import com.prueba.profact.shared.utils.errors_manager.ErroresBindingResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/nits")
public class NitRestController {

    /** Servicios para nit */
    private final INitServices nitServices;

    /** Claves de mensajes de respuestas en captura de errores */
    private final String MENSAJE = "mensaje";
    private final String STATUS = "status";

    /**
     * Busca un nit por el código
     * 
     * @param documento documento a buscar
     * @param result    errores almacenados durante la validación de la solicitud
     * @return
     */
    @PostMapping("/documento")
    public ResponseEntity<ResponseData> buscarPorDocumento(@Valid @RequestBody String documento, BindingResult result) {

        ResponseData responseData = new ResponseData();

        if (result.hasErrors()) {
            List<String> errors = ErroresBindingResult.recuperarErrores(result);

            responseData.setMensaje(MensajesError.CLIENTE.lanzar());
            responseData.setData(errors);

            return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            NitDTO artRespuesta = NitControllerMapper.INSTANCIA.baseToDto(
                    nitServices.buscarPorDocumento(documento));

            if (artRespuesta != null) {
                responseData.setMensaje(MensajesRespuesta.ENCONTRADA.responder());
                responseData.setData(artRespuesta);
            } else {
                throw new NitControllerException(MensajesRespuesta.NO_ENCONTRADA.responder());
            }

        } catch (Exception e) {
            System.out.println("Mensaje de error NitRestController/buscarPorDocumento: " + e);
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
