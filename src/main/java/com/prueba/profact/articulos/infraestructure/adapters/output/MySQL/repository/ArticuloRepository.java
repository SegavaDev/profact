package com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.profact.articulos.application.ports.output.IArticuloRepository;
import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.exceptions.ArticuloException;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.exceptions.NoFoundArticuloException;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.mappers.ArticuloMapperOut;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.models.ArticuloEntity;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ArticuloRepository implements IArticuloRepository {

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    /** Query para buscar un artículo por su código */
    private final String BUSCAR_CODIGO = "SELECT a FROM articulos a WHERE a.artCod = ?1";

    /** Query para buscar un artículo por su código */
    private final String BUSCAR_CODIGO_LIST = "SELECT a FROM articulos a WHERE a.artCod LIKE CONCAT('%',?1,'%')";

    /**
     * Busca un artículo por su Id
     * 
     * @param id id del artículo
     * @return un optional con el artículo encontrado o vacío
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Articulo> buscarPorId(final long id) throws ArticuloException {
        try {
            return Optional.of(
                    ArticuloMapperOut.INSTANCIA.entityToBase(
                            this.ENTITY_MANAGER.find(ArticuloEntity.class, id)));

        } catch (NoResultException n) {
            System.out.println("Error ArticuloRepository/buscarPorId: " + n);
            throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
        } catch (Exception e) {
            System.out.println("Error ArticuloRepository/buscarPorId: " + e);
            throw new ArticuloException(MensajesError.INTERNO.lanzar());
        }
    }

    /**
     * Busca un artículo por su código
     * 
     * @param codigo código del artículo
     * @return un optional con el artículo encontrado o vacío
     * @throws NoFoundArticuloException
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<Articulo> buscarPorCodigo(final String codigo) throws ArticuloException, NoFoundArticuloException {
        try {
            return Optional.of(
                    ArticuloMapperOut.INSTANCIA.entityToBase(
                            this.ENTITY_MANAGER.createQuery(this.BUSCAR_CODIGO, ArticuloEntity.class)
                                    .setParameter(1, codigo)
                                    .getSingleResult()));

        } catch (NoResultException n) {
            System.out.println("Error ArticuloRepository/buscarPorCodigo: " + n);
            throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
        } catch (Exception e) {
            System.out.println("Error ArticuloRepository/buscarPorCodigo: " + e);
            throw new ArticuloException(MensajesError.INTERNO.lanzar());
        }
    }

    /**
     * Busca un artículo por su código
     * 
     * @param codigo código del artículo
     * @return un optional con lista de artículos encontrado o vacío
     * @throws NoFoundArticuloException
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<List<Articulo>> buscarPorCodigoList(final String codigo) throws ArticuloException, NoFoundArticuloException {
        try {
            return Optional.of(
                    ArticuloMapperOut.INSTANCIA.entityToBase(
                            this.ENTITY_MANAGER.createQuery(this.BUSCAR_CODIGO_LIST, ArticuloEntity.class)
                                    .setParameter(1, codigo)
                                    .getResultList()));

        } catch (NoResultException n) {
            System.out.println("Error ArticuloRepository/buscarPorCodigo: " + n);
            throw new NoResultException(MensajesError.NO_ENCONTRADO.lanzar());
        } catch (Exception e) {
            System.out.println("Error ArticuloRepository/buscarPorCodigo: " + e);
            throw new ArticuloException(MensajesError.INTERNO.lanzar());
        }
    }

    /**
     * Actualiza un artículo en la base de datos
     * 
     * @param articulo el articulo que contiene la información a actualizar
     * @return true si fue posible la actualización
     */
    @Transactional
    @Override
    public boolean actualizar(final Articulo articulo) throws ArticuloException {
        try {
            return this.ENTITY_MANAGER.merge(
                    ArticuloMapperOut.INSTANCIA.baseToEntity(articulo)) != null ? true : false;

        } catch (Exception e) {
            System.out.println("Error ArticuloRepository/actualizar: " + e);
            throw new ArticuloException(MensajesError.INTERNO.lanzar());
        }
    }

}
