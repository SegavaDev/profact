package com.prueba.profact.kardex_factura.infraestructure.adapters.input.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.kardex_factura.infraestructure.adapters.input.rest.models.KardexFacturaDTO;

/**
 * Mapea las clases y DTO's en infraestructura
 */
@Mapper
public interface KardexFacturaControllerMapper {

  /** Instancia del mapeo */
  KardexFacturaControllerMapper INSTANCIA = Mappers.getMapper(KardexFacturaControllerMapper.class);

  /**
   * Mapea un DTO a una clase de dominio
   * 
   * @param kardexFacturaDTO clase a mapear
   * @return clase base de dominio
   */
  KardexFactura dtoToBase(final KardexFacturaDTO kardexFacturaDTO);

  /**
   * Mapea una clase de dominio a un DTO
   * 
   * @param kardexFactura clase a mapear
   * @return clase DTO
   */
  KardexFacturaDTO baseToDto(final KardexFactura kardexFactura);

}
