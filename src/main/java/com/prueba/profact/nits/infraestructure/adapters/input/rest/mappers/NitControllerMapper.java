package com.prueba.profact.nits.infraestructure.adapters.input.rest.mappers;

import java.util.List;

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

    /**
     * Mapea una lista de DTO a una de clases de dominio
     * @param nitDTOList clases a mapear
     * @return clases base de dominio
      */
      @Mapping(target = "nitId", ignore = true)
      List<Nit> dtoToBase(final List<NitDTO> nitDTOList);
  
      /**
       * Mapea una lista de clases de dominio a una de DTO
       * @param nitList clases a mapear
       * @return clases DTO
        */
      List<NitDTO> baseToDto(final List<Nit> nitList);
    
}
