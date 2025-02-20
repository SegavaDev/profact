package com.prueba.profact.articulos.application.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prueba.profact.articulos.application.exceptions.ArticuloServicesException;
import com.prueba.profact.articulos.application.exceptions.NoFoundArticuloException;
import com.prueba.profact.articulos.application.ports.input.IArticuloServices;
import com.prueba.profact.articulos.application.ports.output.IArticuloRepository;
import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.shared.domain.models.dtos.SharedArticuloActSaldoDTO;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArticulosServices implements IArticuloServices {

    /**
     * Comunicación con el repositorio y sus métodos de persistencia
     */
    private final IArticuloRepository ARTICULO_REPOSITORY;

    @Override
    public Articulo buscarPorId(final long id) throws ArticuloServicesException {
        try {
            return this.ARTICULO_REPOSITORY.buscarPorId(id)
                    .orElseThrow(
                            () -> new NoFoundArticuloException(MensajesError.NO_ENCONTRADO.lanzar()));

        } catch (NoResultException n) {
            System.out.println("Mensaje error ArticulosServices/buscarPorId: " + n);
            throw new NoResultException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error ArticulosServices/buscarPorId: " + e);
            throw new ArticuloServicesException(e.getMessage());
        }

    }

    @Override
    public Articulo buscarPorCodigo(final String codigo) throws ArticuloServicesException, NoFoundArticuloException {
        try {
            return this.ARTICULO_REPOSITORY.buscarPorCodigo(codigo)
                    .orElseThrow(
                            () -> new NoFoundArticuloException(MensajesError.NO_ENCONTRADO.lanzar()));

        } catch (EmptyResultDataAccessException n) {
            System.out.println("Mensaje error ArticulosServices/buscarPorCodigo: " + n);
            throw new NoFoundArticuloException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error ArticulosServices/buscarPorCodigo: " + e);
            throw new ArticuloServicesException(e.getMessage());
        }
    }

    @Override
    public boolean actualizarSaldo(final SharedArticuloActSaldoDTO articulo, final boolean naturaleza)
            throws ArticuloServicesException {
        try {
            Articulo artValid = this.ARTICULO_REPOSITORY.buscarPorCodigo(articulo.getArtCod()).orElse(null);

            if (artValid != null) {
                if (naturaleza) {
                    artValid.aumentarSaldo(articulo.getArtSaldo());
                    return true;
                } else {
                    return artValid.reducirSaldo(articulo.getArtSaldo());
                }
            } else {
                return false;
            }

        } catch (NoResultException n) {
            System.out.println("Mensaje error ArticulosServices/actualizarSaldo: " + n);
            throw new NoResultException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error ArticulosServices/actualizarSaldo: " + e);
            throw new ArticuloServicesException(e.getMessage());
        }
    }

}
