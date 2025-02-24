package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.profact.factura.application.ports.output.IFacturaRepository;
import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.FacturaException;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.mappers.FacturaMapperOut;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models.FacturaEntity;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FacturaRepository implements IFacturaRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /** Query buscar por consecutivo */
    final String BUSCAR_CONSECUTIVO = "SELECT f FROM facturas f WHERE f.factNum = ?1";

    /**
     * Persiste una factura
     * 
     * @param factura factura a persistir en base de datos
     */
    @Transactional
    @Override
    public Factura generarFactura(final Factura factura) throws FacturaException {
        try {
            this.ENTITY_MANAGER.persist(
                    FacturaMapperOut.INSTANCIA.baseToEntity(factura));

            return buscFacturaPorNumero(factura.getFactNum());

        } catch (Exception e) {
            System.out.println("Error FacturaRepository/generarFactura: " + e);
            throw new FacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Factura buscFacturaPorId(final long id) throws FacturaException {
        try {
            return FacturaMapperOut.INSTANCIA.entityToBase(ENTITY_MANAGER.find(FacturaEntity.class, id));

        } catch (Exception e) {
            System.out.println("Error FacturaRepository/buscFacturaPorId: " + e);
            throw new FacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Factura buscFacturaPorNumero(final String numero) throws FacturaException {
        try {
            return FacturaMapperOut.INSTANCIA.entityToBase(
                    ENTITY_MANAGER.createQuery(BUSCAR_CONSECUTIVO, FacturaEntity.class)
                    .setParameter(1, numero)
                    .getSingleResult());

        } catch (Exception e) {
            System.out.println("Error FacturaRepository/buscFacturaPorNumero: " + e);
            throw new FacturaException(MensajesError.INTERNO.lanzar());
        }
    }

}
