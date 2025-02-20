package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.repository;

import org.springframework.stereotype.Repository;

import com.prueba.profact.factura.application.ports.output.IFacturaRepository;
import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.FacturaException;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.mappers.FacturaMapperOut;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FacturaRepository implements IFacturaRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /** 
     * Persiste una factura
     * @param factura factura a persistir en base de datos
     */
    @Override
    public boolean generarFactura(final Factura factura) throws FacturaException {
        try {
            this.ENTITY_MANAGER.persist(
                FacturaMapperOut.INSTANCIA.baseToEntity(factura)
            );

            return true;

        } catch (Exception e) {
            System.out.println("Error FacturaRepository/generarFactura: " + e);
            throw new FacturaException(MensajesError.INTERNO.lanzar());
        }
    }

}
