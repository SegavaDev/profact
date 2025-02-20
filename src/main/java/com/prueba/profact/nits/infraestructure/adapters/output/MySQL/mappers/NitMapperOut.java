package com.prueba.profact.nits.infraestructure.adapters.output.MySQL.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.nits.infraestructure.adapters.output.MySQL.models.NitEntity;

@Mapper
public interface NitMapperOut {
    
    NitMapperOut INSTANCIA = Mappers.getMapper(NitMapperOut.class);

    /**
     * Mapea una clase de tipo entidad a clase de dominio
     * @param nitEntity clase entidad
     * @return clase de dominio
      */
    Nit entityToBase(final NitEntity nitEntity);

    /**
     * Mapea una clase de dominio a una de tipo entidad
     * @param nit clase de dominio
     * @return clase de tipo entidad
      */
    NitEntity baseToEntity(final Nit nit);

}
