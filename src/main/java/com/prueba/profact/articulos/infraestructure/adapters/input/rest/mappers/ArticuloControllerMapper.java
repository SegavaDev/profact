package com.prueba.profact.articulos.infraestructure.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.articulos.infraestructure.adapters.input.rest.models.ArticuloDTO;

/**
 * Mapea las clases y DTO's en infraestructura
  */
@Mapper
public interface ArticuloControllerMapper {

    /** Instancia del mapeo */
    ArticuloControllerMapper INSTANCIA = Mappers.getMapper(ArticuloControllerMapper.class);

    /**
     * Mapea un DTO a una clase de dominio
     * @param articuloDTO clase a mapear
     * @return clase base de dominio
      */
    Articulo dtoToBase(final ArticuloDTO articuloDTO);

    /**
     * Mapea una clase de dominio a un DTO
     * @param articulo clase a mapear
     * @return clase DTO
      */
    ArticuloDTO baseToDto(final Articulo articulo);
    
}
