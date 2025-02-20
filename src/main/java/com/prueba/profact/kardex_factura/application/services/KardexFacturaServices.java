package com.prueba.profact.kardex_factura.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.profact.kardex_factura.application.exceptions.KardexFacturaServicesException;
import com.prueba.profact.kardex_factura.application.ports.input.IKardexFacturaServices;
import com.prueba.profact.kardex_factura.application.ports.output.IKardexFacturaRepository;
import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KardexFacturaServices implements IKardexFacturaServices {

    /**
     * Comunicación con el repositorio y sus métodos de persistencia
     */
    private final IKardexFacturaRepository KARDEX_FACT_REPOSITORY;

    @Override
    public boolean guardarKardex(List<KardexFactura> listkardexFactura) throws KardexFacturaServicesException {
        try {
            return this.KARDEX_FACT_REPOSITORY.guardarKardex(listkardexFactura);
            
        } catch (Exception e) {
            System.out.println("Error KardexFacturaServices/guardarKardex: " + e);
            throw new KardexFacturaServicesException(MensajesError.INTERNO.lanzar());
        }
    }   

}
