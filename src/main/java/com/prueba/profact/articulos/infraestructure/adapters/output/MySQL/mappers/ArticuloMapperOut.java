package com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.articulos.domain.models.Articulo;
import com.prueba.profact.articulos.infraestructure.adapters.output.MySQL.models.ArticuloEntity;

@Mapper
public interface ArticuloMapperOut {
    
    ArticuloMapperOut INSTANCIA = Mappers.getMapper(ArticuloMapperOut.class);

    /**
     * Mapea una clase de tipo entidad a clase de dominio
     * @param articuloEntity clase entidad
     * @return clase de dominio
      */
    Articulo entityToBase(final ArticuloEntity articuloEntity);

    /**
     * Mapea una clase de dominio a una de tipo entidad
     * @param articulo clase de dominio
     * @return clase de tipo entidad
      */
    ArticuloEntity baseToEntity(final Articulo articulo);

}
