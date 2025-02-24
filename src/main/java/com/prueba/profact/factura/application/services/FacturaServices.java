package com.prueba.profact.factura.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.profact.factura.application.exceptions.NoCreateConsecutivoFactException;
import com.prueba.profact.factura.application.exceptions.NoCreateFactException;
import com.prueba.profact.factura.application.ports.input.IFacturaServices;
import com.prueba.profact.factura.application.ports.output.IConsFacturaRepository;
import com.prueba.profact.factura.application.ports.output.IFacturaRepository;
import com.prueba.profact.factura.domain.models.ConsecutivoFactura;
import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.exceptions.ConsecutivoFacturaException;
import com.prueba.profact.shared.domain.models.enums.MensajesError;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FacturaServices implements IFacturaServices {

    /**
     * Comunicación con el repositorio y sus métodos de persistencia
     */
    private final IFacturaRepository FACTURA_REPOSITORY;
    private final IConsFacturaRepository CONS_FACT_REPOSITORY;

    @Override
    public ConsecutivoFactura generarConsecutivo() throws ConsecutivoFacturaException {
        try {
            final Optional<ConsecutivoFactura> CONSECUTIVO = this.CONS_FACT_REPOSITORY.generarConsecutivo();

            if(CONSECUTIVO.isPresent()) {
                return CONSECUTIVO.get();
            }
            else {
                throw new NoCreateConsecutivoFactException(MensajesError.CONSECUTIVO_NO_CREADO.lanzar());
            }
            
        } catch (Exception e) {
            System.out.println("Error FacturaServices/generarConsecutivo: " + e);
            throw new ConsecutivoFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    @Override
    public boolean asignarConsecutivo(final long factId, final String factNum) throws ConsecutivoFacturaException {
        try {
            return this.CONS_FACT_REPOSITORY.asignarConsecutivo(factId, factNum);
            
        } catch (Exception e) {
            System.out.println("Error FacturaServices/asignarConsecutivo: " + e);
            throw new ConsecutivoFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    @Override
    public Factura generarFactura(final Factura factura) throws ConsecutivoFacturaException {
        try {
            Factura rpFactura = this.FACTURA_REPOSITORY.generarFactura(factura);
            if(rpFactura != null) {
                if(this.asignarConsecutivo(factura.getFactId(), factura.getFactNum())) {
                    System.out.println("\nFactura " + factura.getFactNum() + " asignada a consecutivo exitosamente.\n");
                }
                return rpFactura;
            }
            else {
                throw new NoCreateFactException(MensajesError.FACTURA_NO_CREADA.lanzar());
            }
            
        } catch (Exception e) {
            System.out.println("Error FacturaServices/generarFactura: " + e);
            throw new ConsecutivoFacturaException(MensajesError.INTERNO.lanzar());
        }
    }

    

}
