package com.prueba.profact.nits.infraestructure.adapters.output.MySQL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.profact.nits.application.exceptions.NitServicesException;
import com.prueba.profact.nits.application.ports.output.INitRepository;
import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.exceptions.NitException;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.exceptions.NoFoundNitException;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.mappers.NitMapperOut;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.NitEntity;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.dtos.NitActCarteraDto;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NitRepository implements INitRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /** Query para buscar un nit por su documento */
    private final String BUSCAR_DOCUMENTO = "SELECT n FROM nits n WHERE n.nitDoc = ?1";
    private final String BUSCAR_DOCUMENTO_LIST = "SELECT n FROM nits n WHERE n.nitDoc LIKE CONCAT('%', ?1, '%')";

    /**
     * Busca un nit por su Id
     * 
     * @param id id del nit
     * @return un optional con el nit encontrado o vacío
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Nit> buscarPorId(final long id) throws NitException {
        System.out.println("Nit id: " + id);
        try {
            return Optional.of(
                    NitMapperOut.INSTANCIA.entityToBase(
                            this.ENTITY_MANAGER.find(NitEntity.class, id)));

        } catch (NoResultException n) {
            System.out.println("Error NitRepository/buscarPorId: " + n);
            throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
        } catch (Exception e) {
            System.out.println("Error NitRepository/buscarPorId: " + e);
            throw new NitException(MensajesError.INTERNO.lanzar());
        }
    }

    /**
     * Busca un nit por su documento
     * 
     * @param documento documento del nit
     * @return un optional con el nit encontrado o vacío
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Nit> buscarPorDocumento(final String documento) throws NitException, NoFoundNitException {
        try {
            return Optional.of(
                    NitMapperOut.INSTANCIA.entityToBase(
                            this.ENTITY_MANAGER.createQuery(this.BUSCAR_DOCUMENTO, NitEntity.class)
                                    .setParameter(1, documento)
                                    .getSingleResult()));

        } catch (NoResultException n) {
            System.out.println("Error NitRepository/buscarPorDocumento: " + n);
            throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
        } catch (Exception e) {
            System.out.println("Error NitRepository/buscarPorDocumento: " + e);
            throw new NitException(MensajesError.INTERNO.lanzar());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Nit>> buscarListPorDocumento(final String documento) throws NitException, NoFoundNitException,
            NitServicesException, com.prueba.profact.nits.application.exceptions.NoFoundNitException {
                try {
                    return Optional.of(
                            NitMapperOut.INSTANCIA.entityToBase(
                                    this.ENTITY_MANAGER.createQuery(this.BUSCAR_DOCUMENTO_LIST, NitEntity.class)
                                            .setParameter(1, documento)
                                            .getResultList()));
        
                } catch (NoResultException n) {
                    System.out.println("Error NitRepository/buscarPorDocumento: " + n);
                    throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
                } catch (Exception e) {
                    System.out.println("Error NitRepository/buscarPorDocumento: " + e);
                    throw new NitException(MensajesError.INTERNO.lanzar());
                }
    }

    /**
     * Actualiza un nit en la base de datos
     * 
     * @param nit el nit que contiene la información a actualizar
     * @return true si fue posible la actualización
     */
    @Transactional
    @Override
    public boolean actualizar(final Nit nit) throws NitException {
        try {
            return this.ENTITY_MANAGER.merge(
                    NitMapperOut.INSTANCIA.baseToEntity(nit)) != null ? true : false;

        } catch (Exception e) {
            System.out.println("Error NitRepository/actualizar: " + e);
            throw new NitException(MensajesError.INTERNO.lanzar());
        }
    }

    /** 
     * Actualiza la cartera del cliente/nit
     */
    @Transactional
    @Override
    public long actualizar(NitActCarteraDto nitActPostFactDto) throws NitException, NitServicesException {
        try {
            Optional<Nit> nit = this.buscarPorDocumento(nitActPostFactDto.getNitDoc());
            
            if(nit.isPresent()) {
                nit.get().agregarCartera(nitActPostFactDto.getNitCartera());

                return this.actualizar(nit.get()) ? nit.get().getNitId() : -1;
            }
            else {
                return -1;
            }
            
        } catch (Exception e) {
            System.out.println("Error NitRepository/actualizar NitActPostFactDto: " + e);
            throw new NitException(MensajesError.INTERNO.lanzar());
        }
    }

}
