package com.prueba.profact.factura.application.services;

import org.springframework.stereotype.Service;

import com.prueba.profact.factura.application.exceptions.SharedFacturaServicesException;
import com.prueba.profact.factura.application.ports.input.IFacturaServices;
import com.prueba.profact.factura.application.ports.input.ISharedFacturaServices;
import com.prueba.profact.factura.domain.mappers.FacturaDomainMapper;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SharedFacturaServices implements ISharedFacturaServices {

    /**
     * Servicios de slice factura
     */
    private final IFacturaServices FACTURA_SERVICES;

    @Override
    public SharedFacturaDTO generarFactura(final SharedFacturaDTO sharedFacturaDTO) throws SharedFacturaServicesException {
        try {
            return FacturaDomainMapper.INSTANCIA.baseTosharedDto(
                    this.FACTURA_SERVICES.generarFactura(
                            FacturaDomainMapper.INSTANCIA.sharedDtoToBase(sharedFacturaDTO)));

        } catch (Exception e) {
            System.out.println("Error SharedFacturaServices/generarFactura: " + e);
            throw new SharedFacturaServicesException(MensajesError.INTERNO.lanzar());
        }
    }

}
