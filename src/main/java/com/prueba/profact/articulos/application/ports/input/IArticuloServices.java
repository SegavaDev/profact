package com.prueba.profact.articulos.application.ports.input;

import java.util.List;

import com.prueba.profact.articulos.application.exceptions.ArticuloServicesException;
import com.prueba.profact.articulos.application.exceptions.NoFoundArticuloException;
import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.shared.domain.models.dtos.SharedArticuloActSaldoDTO;

/**
 * Interfaz con servicios disponibles
 */
public interface IArticuloServices {

  /**
   * Busca un artículo por su ID
   * 
   * @param id id a buscar
   * @return la clase Articulo
   * @throws ArticuloServicesException maneja las excepciones
   */
  Articulo buscarPorId(final long id) throws ArticuloServicesException;

  /**
   * Busca un artículo por su código
   * 
   * @param codigo código a buscar
   * @return la clase Articulo
   * @throws ArticuloServicesException maneja las excepciones
   * @throws NoFoundArticuloException
   */
  Articulo buscarPorCodigo(final String codigo) throws ArticuloServicesException, NoFoundArticuloException;

  /**
   * Busca un artículo por su código
   * 
   * @param codigo código a buscar
   * @return lista de clase Articulo
   * @throws ArticuloServicesException maneja las excepciones
   * @throws NoFoundArticuloException
   */
  List<Articulo> buscarPorCodigoList(final String codigo) throws ArticuloServicesException, NoFoundArticuloException;

  /**
   * Actualiza el saldo en inventario de un artículo
   * 
   * @param articulo   el articulo que se va a afectar
   * @param naturaleza true para aumentar su valor, false para disminuir
   * @return true si fue posible actualizar el saldo
   * @throws ArticuloServicesException maneja las excepciones
   */
  boolean actualizarSaldo(final SharedArticuloActSaldoDTO articulo, final boolean naturaleza)
      throws ArticuloServicesException;

}
