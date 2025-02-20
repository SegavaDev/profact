package com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.prueba.profact.kardex_factura.application.ports.output.IKardexFacturaRepository;
import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.exceptions.KardexFacturaException;
import com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.mappers.KardexFactMapperOut;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class KardexFacturaRepository implements IKardexFacturaRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /**
     * Persiste una lista de kardex
     * 
     * @param listkardexFactura lista de kardex a persistir en base de datos
     * @return true si la persistencia fue exitosa
     * @throws KardexFacturaException
     */
    @Override
    public boolean guardarKardex(final List<KardexFactura> listkardexFactura) throws KardexFacturaException {
        try {
            listkardexFactura.stream()
                    .forEach(
                            k -> this.ENTITY_MANAGER.persist(
                                    KardexFactMapperOut.INSTANCIA.baseToEntity(k)));

            return true;

        } catch (Exception e) {
            System.out.println("Error KardexFacturaRepository/guardarKardex: " + e);
            throw new KardexFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

}
