package com.prueba.profact.factura.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedFacturaDTO;

/**
 * Mapea las clases y DTO's en infraestructura
 */
@Mapper
public interface FacturaDomainMapper {

  /** Instancia del mapeo */
  FacturaDomainMapper INSTANCIA = Mappers.getMapper(FacturaDomainMapper.class);

  /**
   * Mapea una clase compartida a clase de dominio
   * @param sharedFacturaDTO clase compartida
   * @return clase de dominio
    */
  @Mapping(target = "factId", ignore = true)
  @Mapping(target = "krdxFactIds", ignore = true)
  Factura sharedDtoToBase(final SharedFacturaDTO sharedFacturaDTO);

  /**
   * Mapea una clase de dominio a una clase compartida
   * @param factura clase de dominio
   * @return clase compartida
    */
  SharedFacturaDTO baseTosharedDto(final Factura factura);

}
