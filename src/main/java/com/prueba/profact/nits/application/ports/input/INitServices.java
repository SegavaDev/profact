package com.prueba.profact.nits.application.ports.input;

import java.util.List;

import com.prueba.profact.nits.application.exceptions.NitServicesException;
import com.prueba.profact.nits.application.exceptions.NoFoundNitException;
import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.dtos.NitActCarteraDto;

/**
 * Interfaz con servicios disponibles
 */
public interface INitServices {

  /**
   * Busca un nit por su ID
   * 
   * @param id id a buscar
   * @return la clase Nit
   * @throws NitServicesException
   */
  Nit buscarPorId(final long id) throws NitServicesException;

  /**
   * Busca un nit por su documento
   * 
   * @param documento documento a buscar
   * @return la lista de clases Nit
   * @throws NoFoundNitException
   * @throws NitServicesException
   */
  List<Nit> buscarPorDocumento(final String documento) throws NitServicesException, NoFoundNitException;

  /**
   * Actualiza un nit en la base de datos
   * 
   * @param nit el nit que contiene la información a actualizar
   * @return true si fue posible la actualización
   * @throws NitServicesException
   */
  boolean actualizar(final Nit nit) throws NitServicesException;

  /**
   * Actualiza un nit en la base de datos
   * 
   * @param nitActCarteraDto información necesaria para actualizar la cartera del nit
   * @return true si fue posible la actualización
 * @throws NitServicesException 
   */
  long actualizar(final NitActCarteraDto nitActCarteraDto) throws NitServicesException;

}
