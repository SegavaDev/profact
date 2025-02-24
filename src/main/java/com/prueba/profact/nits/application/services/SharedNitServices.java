package com.prueba.profact.nits.application.services;

import org.springframework.stereotype.Service;

import com.prueba.profact.nits.application.exceptions.SharedNitServicesException;
import com.prueba.profact.nits.application.ports.input.INitServices;
import com.prueba.profact.nits.application.ports.input.ISharedNitServices;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.dtos.NitActCarteraDto;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SharedNitServices implements ISharedNitServices {

    /**
     * Comunicación con los servicios principales de nit
     */
    private final INitServices NIT_SERVICES;

    @Override
    public long actualizar(final SharedFacturaDTO sharedFacturaDTO) throws SharedNitServicesException {
        try {
            return this.NIT_SERVICES.actualizar(
                new NitActCarteraDto(sharedFacturaDTO.getFactNitDoc(), sharedFacturaDTO.getFactTotalVent())
            );

        } catch (Exception e) {
            System.out.println("Mensaje error NitServices/actualizarSaldo: " + e);
            throw new SharedNitServicesException(e.getMessage());
        }
    }

}
