package com.prueba.profact.nits.application.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.prueba.profact.nits.application.exceptions.NitNoCupoException;
import com.prueba.profact.nits.application.exceptions.NitNoPlazoException;
import com.prueba.profact.nits.application.exceptions.NitServicesException;
import com.prueba.profact.nits.application.exceptions.NoFoundNitException;
import com.prueba.profact.nits.application.ports.input.INitServices;
import com.prueba.profact.nits.application.ports.output.INitRepository;
import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NitServices implements INitServices {

    /**
     * Comunicación con el repositorio y sus métodos de persistencia
     */
    private final INitRepository NIT_REPOSITORY;

    @Override
    public Nit buscarPorId(final long id) throws NitServicesException {
        try {
            return this.NIT_REPOSITORY.buscarPorId(id)
                    .orElseThrow(
                            () -> new NoFoundNitException(MensajesError.NO_ENCONTRADO.lanzar()));

        } catch (NoResultException n) {
            System.out.println("Mensaje error NitServices/buscarPorId: " + n);
            throw new NoResultException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error NitServices/buscarPorId: " + e);
            throw new NitServicesException(e.getMessage());
        }

    }

    @Override
    public Nit buscarPorDocumento(final String documento) throws NitServicesException, NoFoundNitException {
        try {
            return this.NIT_REPOSITORY.buscarPorDocumento(documento)
                    .orElseThrow(
                            () -> new NoFoundNitException(MensajesError.NO_ENCONTRADO.lanzar()));

        } catch (EmptyResultDataAccessException n) {
            System.out.println("Mensaje error NitServices/buscarPorDocumento: " + n);
            throw new NoFoundNitException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error NitServices/buscarPorDocumento: " + e);
            throw new NitServicesException(e.getMessage());
        }
    }

    @Override
    public boolean actualizar(final Nit nit) throws NitServicesException {
        try {
            Nit nitValid = this.NIT_REPOSITORY.buscarPorDocumento(nit.getNitDoc()).orElse(null);

            if(nitValid == null) {
                return false;
            }
            if(!nitValid.tieneCupo(nit.getNitCartera())) {
                throw new NitNoCupoException(MensajesError.CUPO.lanzar());
            }
            if(!nitValid.hayPlazo(nit.getNitPlazo())) {
                throw new NitNoPlazoException(MensajesError.PLAZO.lanzar());
            }

            return this.NIT_REPOSITORY.actualizar(nit);

        } catch (NoResultException n) {
            System.out.println("Mensaje error NitServices/actualizarSaldo: " + n);
            throw new NoResultException(n.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje error NitServices/actualizarSaldo: " + e);
            throw new NitServicesException(e.getMessage());
        }
    }

}
