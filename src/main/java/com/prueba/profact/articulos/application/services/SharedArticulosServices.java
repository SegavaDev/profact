package com.prueba.profact.articulos.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.profact.articulos.application.exceptions.SharedArticuloServicesException;
import com.prueba.profact.articulos.application.ports.input.IArticuloServices;
import com.prueba.profact.articulos.application.ports.input.ISharedArticuloServices;
import com.prueba.profact.shared.domain.models.dtos.SharedArticuloActSaldoDTO;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedKardexFacturaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SharedArticulosServices implements ISharedArticuloServices {

    /** Servicios de artículos */
    private final IArticuloServices ARTICULO_SERVICES;

    @Override
    public boolean actualizarSaldo(final List<SharedKardexFacturaDTO> articulosKardex)
            throws SharedArticuloServicesException {
        try {
            articulosKardex.stream().forEach(
                    ak -> {
                        try {
                            this.ARTICULO_SERVICES.actualizarSaldo(
                                    new SharedArticuloActSaldoDTO(ak.getKrdxArtId(), ak.getKrdxArtCod(),
                                            ak.getKrdxUnds(), ak.isKrdxArtNat()));

                        } catch (Exception e) {
                            System.out.println("Mensaje error SharedArticulosServices stream/actualizarSaldo: " + e);
                        }
                    });

            return true;

        } catch (Exception e) {
            System.out.println("Mensaje error SharedArticulosServices/actualizarSaldo: " + e);
            throw new SharedArticuloServicesException(e.getMessage());
        }
    }

}
