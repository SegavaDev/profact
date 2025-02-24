package com.prueba.profact.kardex_factura.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.profact.articulos.application.ports.input.ISharedArticuloServices;
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
    /**
     * Interfaz de servicios de artículos compartidos
     */
    private final ISharedArticuloServices SHARED_ARTICULOS_SERVICES;

    @Override
    public boolean guardarListaKardex(final List<SharedKardexFacturaDTO> sharedKardexDto) throws SharedKardexFacturaServicesException {
        try {
            final boolean kardexOk = this.KARDEX_FACTURA_SERVICES.guardarKardex(
                    KardexFacturaDomainMapper.INSTANCIA.sharedDtoToBase(sharedKardexDto));
            
            final boolean artOk = this.SHARED_ARTICULOS_SERVICES.actualizarSaldo(sharedKardexDto);

            return kardexOk && artOk;


        } catch (Exception e) {
            System.out.println("Mensaje de error SharedKardexFacturaServices/guardarListaKardex: " + e);
            throw new SharedKardexFacturaServicesException(MensajesError.INTERNO.lanzar());
        }
    }

}
