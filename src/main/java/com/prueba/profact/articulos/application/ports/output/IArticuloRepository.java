package com.prueba.profact.articulos.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.exceptions.ArticuloException;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.exceptions.NoFoundArticuloException;

/**
 * Interfaz con métodos de persistencia disponibles
 */
public interface IArticuloRepository {

  /**
   * Busca un artículo por su ID
   * 
   * @param id id a buscar
   * @return un optional con la clase Articulo si fue encontrada o un null
   * @throws ArticuloException maneja las excepciones
   */
  Optional<Articulo> buscarPorId(final long id) throws ArticuloException;

  /**
   * Busca un artículo por su código
   * 
   * @param codigo código a buscar
   * @return un optional con la clase Articulo si fue encontrada o un null
   * @throws ArticuloException        maneja las excepciones
   * @throws NoFoundArticuloException
   */
  Optional<Articulo> buscarPorCodigo(final String codigo) throws ArticuloException, NoFoundArticuloException;

  /**
   * Busca un artículo por su código
   * 
   * @param codigo código a buscar
   * @return un optional con lista de clase Articulo si fue encontrada o un null
   * @throws ArticuloException        maneja las excepciones
   * @throws NoFoundArticuloException
   */
  Optional<List<Articulo>> buscarPorCodigoList(final String codigo) throws ArticuloException, NoFoundArticuloException;

  /**
   * Actualiza el saldo en inventario de un artículo
   * 
   * @param articulo el articulo que se va a afectar
   * @return true si fue posible actualizar el saldo
   * @throws ArticuloException maneja las excepciones
   */
  boolean actualizar(final Articulo articulo) throws ArticuloException;

}
