package com.prueba.profact.factura.infraestructure.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.input.rest.models.FacturaDTO;

/**
 * Mapea las clases y DTO's en infraestructura
 */
@Mapper
public interface FacturaControllerMapper {

  /** Instancia del mapeo */
  final FacturaControllerMapper INSTANCIA = Mappers.getMapper(FacturaControllerMapper.class);

  /**
   * Mapea un DTO a una clase de dominio
   * 
   * @param facturaDTO clase a mapear
   * @return clase base de dominio
   */
  @Mapping(target = "factId", ignore = true)
  Factura dtoToBase(final FacturaDTO facturaDTO);

  /**
   * Mapea una clase de dominio a un DTO
   * 
   * @param factura clase a mapear
   * @return clase DTO
   */
  FacturaDTO baseToDto(final Factura factura);

}
