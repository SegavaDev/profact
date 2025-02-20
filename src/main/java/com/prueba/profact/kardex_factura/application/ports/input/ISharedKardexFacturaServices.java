package com.prueba.profact.kardex_factura.application.ports.input;

import java.util.List;

import com.prueba.profact.kardex_factura.application.exceptions.SharedKardexFacturaServicesException;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedKardexFacturaDTO;

/**
 * Interfaz para conectar servicios compartidos con slice kardex factura
  */
public interface ISharedKardexFacturaServices {
    
    /**
     * Guarda una lista de kardex factura
     * @param sharedKardexDto lista de kardex a guardar
     * @return true si el proceso fue exitoso
     * @throws SharedKardexFacturaServicesException 
      */
    boolean guardarListaKardex(final List<SharedKardexFacturaDTO> sharedKardexDto) throws SharedKardexFacturaServicesException;

}
