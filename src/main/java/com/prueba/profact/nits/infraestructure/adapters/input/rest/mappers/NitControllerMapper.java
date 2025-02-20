package com.prueba.profact.nits.infraestructure.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.nits.domain.models.Nit;
import com.prueba.profact.nits.infraestructure.adapters.input.rest.models.NitDTO;

/**
 * Mapea las clases y DTO's en infraestructura
  */
@Mapper
public interface NitControllerMapper {

    /** Instancia del mapeo */
    NitControllerMapper INSTANCIA = Mappers.getMapper(NitControllerMapper.class);

    /**
     * Mapea un DTO a una clase de dominio
     * @param nitDTO clase a mapear
     * @return clase base de dominio
      */
    @Mapping(target = "nitId", ignore = true)
    Nit dtoToBase(final NitDTO nitDTO);

    /**
     * Mapea una clase de dominio a un DTO
     * @param nit clase a mapear
     * @return clase DTO
      */
    NitDTO baseToDto(final Nit nit);
    
}
