package com.prueba.profact.nits.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.prueba.profact.nits.application.exceptions.NitServicesException;
import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.exceptions.NitException;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.exceptions.NoFoundNitException;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.dtos.NitActCarteraDto;

/**
 * Interfaz con métodos de persistencia disponibles
 */
public interface INitRepository {

  /**
   * Busca un nit por su ID
   * 
   * @param id id a buscar
   * @return la clase Nit
 * @throws NitServicesException 
   */
  Optional<Nit> buscarPorId(final long id) throws NitException, NitServicesException;

  /**
   * Busca un nit por su documento
   * 
   * @param documento documento a buscar
   * @return lista de clases Nit
 * @throws com.prueba.profact.nits.application.exceptions.NoFoundNitException 
 * @throws NitServicesException 
   */
  Optional<List<Nit>> buscarListPorDocumento(final String documento) throws NitException, NoFoundNitException, NitServicesException, com.prueba.profact.nits.application.exceptions.NoFoundNitException;

  /**
   * Busca un nit por su documento
   * 
   * @param documento documento a buscar
   * @return la clase Nit
 * @throws com.prueba.profact.nits.application.exceptions.NoFoundNitException 
 * @throws NitServicesException 
   */
  Optional<Nit> buscarPorDocumento(final String documento) throws NitException, NoFoundNitException, NitServicesException, com.prueba.profact.nits.application.exceptions.NoFoundNitException;

  /**
   * Actualiza un nit en la base de datos
   * 
   * @param nit el nit que contiene la información a actualizar
   * @return true si fue posible la actualización
 * @throws NitServicesException 
   */
  boolean actualizar(final Nit nit) throws NitException, NitServicesException;

  /**
   * Actualiza la cartera de un nit en la base de datos
   * 
   * @param nitActCarteraDto contiene la información a actualizar
   * @return true si fue posible la actualización
 * @throws NitServicesException 
   */
  long actualizar(final NitActCarteraDto nitActCarteraDto) throws NitException, NitServicesException;

}
