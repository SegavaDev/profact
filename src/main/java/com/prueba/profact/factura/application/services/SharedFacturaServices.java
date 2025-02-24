package com.prueba.profact.factura.application.services;

import org.springframework.stereotype.Service;

import com.prueba.profact.factura.application.exceptions.SharedFacturaServicesException;
import com.prueba.profact.factura.application.ports.input.IFacturaServices;
import com.prueba.profact.factura.application.ports.input.ISharedFacturaServices;
import com.prueba.profact.factura.domain.mappers.FacturaDomainMapper;
import com.prueba.profact.nits.application.ports.input.ISharedNitServices;
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

    /**
     * Servicios compartidos de nit
     */
    private final ISharedNitServices SHARED_NIT_SERVICES;

    @Override
    public SharedFacturaDTO generarFactura(final SharedFacturaDTO sharedFacturaDTO)
            throws SharedFacturaServicesException {
        try {

            long nitId = this.SHARED_NIT_SERVICES.actualizar(sharedFacturaDTO);

            if (nitId != -1) {
                sharedFacturaDTO.setFactNitId(nitId);
                SharedFacturaDTO factRp = FacturaDomainMapper.INSTANCIA.baseTosharedDto(
                        this.FACTURA_SERVICES.generarFactura(
                                FacturaDomainMapper.INSTANCIA.sharedDtoToBase(sharedFacturaDTO)));
                return factRp;
            }
            else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error SharedFacturaServices/generarFactura: " + e);
            throw new SharedFacturaServicesException(MensajesError.INTERNO.lanzar());
        }
    }

}
