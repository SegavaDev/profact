package com.prueba.profact.articulos.infraestructure.adapters.input.rest.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.profact.articulos.application.ports.input.IArticuloServices;
import com.prueba.profact.articulos.infraestructure.adapters.input.rest.exceptions.ArticuloControllerException;
import com.prueba.profact.articulos.infraestructure.adapters.input.rest.mappers.ArticuloControllerMapper;
import com.prueba.profact.articulos.infraestructure.adapters.input.rest.models.ArticuloDTO;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.enums.MensajesRespuesta;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.response.ResponseData;
import com.prueba.profact.shared.utils.errors_manager.ErrorMessageManager;
import com.prueba.profact.shared.utils.errors_manager.ErroresBindingResult;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articulos")
public class ArticuloRestController {

    /** Servicios para artículos */
    private final IArticuloServices articuloServices;

    /**
     * Busca un artículo por el código
     * 
     * @param codigo código a buscar
     * @param result errores almacenados durante la validación de la solicitud
     * @return
     */
    @PostMapping("/codigo")
    public ResponseEntity<ResponseData> buscarPorCodigo(@Valid @RequestBody String codigo, BindingResult result) {

        ResponseData responseData = new ResponseData();
        
        if (result.hasErrors()) {
            List<String> errors = ErroresBindingResult.recuperarErrores(result);

            responseData.setMensaje(MensajesError.CLIENTE.lanzar());
            responseData.setData(errors);

            return new ResponseEntity<ResponseData>(responseData, HttpStatus.BAD_REQUEST);
        }

        try {
            ArticuloDTO artRespuesta = ArticuloControllerMapper.INSTANCIA.baseToDto(
                    articuloServices.buscarPorCodigo(codigo));

            if (artRespuesta != null) {
                responseData.setMensaje(MensajesRespuesta.ENCONTRADA.responder());
                responseData.setData(artRespuesta);
            } else {
                throw new ArticuloControllerException(MensajesRespuesta.NO_ENCONTRADA.responder());
            }

        } catch (Exception e) {
            System.out.println("Mensaje de error ArticuloRestController/buscarPorCodigo: " + e);
            final String VALOR = e.getClass().getSimpleName();

            Map<String, Object> resp = ErrorMessageManager.existe(VALOR)
                    ? ErrorMessageManager.valueOf(VALOR).getMensaje()
                    : null;

            final String MENSAJE = resp != null
                    ? resp.get("mensaje").toString()
                    : MensajesError.INTERNO.lanzar();
            final HttpStatus STATUS = resp != null
                    ? (HttpStatus) resp.get("status")
                    : HttpStatus.INTERNAL_SERVER_ERROR;

            responseData.setMensaje(MENSAJE);
            responseData.setData(null);
            return new ResponseEntity<ResponseData>(responseData, STATUS);
        }

        return new ResponseEntity<ResponseData>(responseData, HttpStatus.OK);

    }

}
