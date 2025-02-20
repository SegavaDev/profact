package com.prueba.profact.kardex_factura.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.profact.kardex_factura.application.exceptions.SharedKardexFacturaServicesException;
import com.prueba.profact.kardex_factura.application.ports.input.IKardexFacturaServices;
import com.prueba.profact.kardex_factura.application.ports.input.ISharedKardexFacturaServices;
import com.prueba.profact.kardex_factura.domain.mappers.KardexFacturaDomainMapper;
import com.prueba.profact.shared.domain.models.enums.MensajesError;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedKardexFacturaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SharedKardexFacturaServices implements ISharedKardexFacturaServices {

    /**
     * Interfaz de servicios de slice kardex factura
     */
    private final IKardexFacturaServices KARDEX_FACTURA_SERVICES;

    @Override
    public boolean guardarListaKardex(final List<SharedKardexFacturaDTO> sharedKardexDto) throws SharedKardexFacturaServicesException {
        try {
            return this.KARDEX_FACTURA_SERVICES.guardarKardex(
                    KardexFacturaDomainMapper.INSTANCIA.sharedDtoToBase(sharedKardexDto));

        } catch (Exception e) {
            System.out.println("Mensaje de error SharedKardexFacturaServices/guardarListaKardex: " + e);
            throw new SharedKardexFacturaServicesException(MensajesError.INTERNO.lanzar());
        }
    }

}
