package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.profact.factura.application.ports.output.IConsFacturaRepository;
import com.prueba.profact.factura.domain.models.ConsecutivoFactura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.ConsecutivoFacturaException;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.mappers.FacturaMapperOut;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models.ConsecutivoFacturaEntity;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

/**
 * ! @apiNote Consecutivo factura solo debe permitir crear y consultar, jamas
 * alterar los registros
 */
@RequiredArgsConstructor
@Repository
public class ConsecutivoFacturaRepository implements IConsFacturaRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /**
     * Cantidad de cifras o caracteres que tendrá el consecutivo
     */
    private final String MAX_SIZE = "8";

    /** Query para buscar un consecutivo por su número */
    private final String BUSCAR_CONSNUM = "SELECT cf FROM consecutivos_facturas cf WHERE cf.consFactNum = ?1";

    /** Query para buscar el id mas grande */
    private final String MAX_ID = "SELECT cf FROM consecutivos_facturas cf ORDER BY cf.consFactId Desc LIMIT 1";

    @Transactional
    @Override
    public Optional<ConsecutivoFactura> generarConsecutivo() throws ConsecutivoFacturaException {
        try {
            Optional<ConsecutivoFactura> cFactura = Optional.of(FacturaMapperOut.INSTANCIA
                    .entityToBase(this.ENTITY_MANAGER.createQuery(MAX_ID, ConsecutivoFacturaEntity.class)
                            .getSingleResult()));

            if (cFactura.get().isConsFactActivo()) {
                return this.construirConsecutivo(cFactura.get().getConsFactId() + 1);
            } else {
                return cFactura;
            }

        } catch (NoResultException n) {
            System.out.println("Error ConsecutivoFacturaRepository/generarConsecutivo: " + n);
            Optional<ConsecutivoFactura> cf = this.construirConsecutivo(1);

            return cf;

        } catch (Exception e) {
            System.out.println("Error ConsecutivoFacturaRepository/generarConsecutivo: " + e);
            throw new ConsecutivoFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    @Transactional
    @Override
    public boolean asignarConsecutivo(final long factId, final String factNum) throws ConsecutivoFacturaException {
        try {
            ConsecutivoFacturaEntity cFacturaEntity = this.ENTITY_MANAGER
                    .createQuery(this.BUSCAR_CONSNUM, ConsecutivoFacturaEntity.class)
                    .setParameter(1, factNum)
                    .getSingleResult();

            if (cFacturaEntity != null) {
                cFacturaEntity.setConsFactNum(factNum);
                this.ENTITY_MANAGER.persist(cFacturaEntity);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error ConsecutivoFacturaRepository/generarConsecutivo: " + e);
            throw new ConsecutivoFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    /**
     * Genera un consecutivo de 8 cifras iniciando con 00000001, este método es
     * usado cuando
     * al revisar en la tabla de consecutivos no se encuentra un consecutivo
     * existente o
     * el último consecutivo está activo
     * 
     * @param numId
     * @return
     */
    private Optional<ConsecutivoFactura> construirConsecutivo(final long numId) {
        String size = "%0" + this.MAX_SIZE + "d";
        String consecutivo = String.format(size, numId);

        Optional<ConsecutivoFactura> cf = Optional.of(new ConsecutivoFactura(consecutivo));

        this.ENTITY_MANAGER.persist(
                FacturaMapperOut.INSTANCIA.baseToEntity(cf.get()));

        return cf;
    }

}
