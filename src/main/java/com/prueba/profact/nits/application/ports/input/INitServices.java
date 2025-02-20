package com.prueba.profact.nits.application.ports.input;

import com.prueba.profact.nits.application.exceptions.NitServicesException;
import com.prueba.profact.nits.application.exceptions.NoFoundNitException;
import com.prueba.profact.nits.domain.models.Nit;

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
   * @return la clase Nit
   * @throws NoFoundNitException
   * @throws NitServicesException
   */
  Nit buscarPorDocumento(final String documento) throws NitServicesException, NoFoundNitException;

  /**
   * Actualiza un nit en la base de datos
   * 
   * @param nit el nit que contiene la información a actualizar
   * @return true si fue posible la actualización
   * @throws NitServicesException
   */
  boolean actualizar(final Nit nit) throws NitServicesException;

}
