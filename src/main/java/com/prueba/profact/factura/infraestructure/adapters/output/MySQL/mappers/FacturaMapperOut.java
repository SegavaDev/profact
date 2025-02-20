package com.prueba.profact.factura.infraestructure.adapters.output.MySQL.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.factura.domain.models.ConsecutivoFactura;
import com.prueba.profact.factura.domain.models.Factura;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models.ConsecutivoFacturaEntity;
import com.prueba.profact.factura.infraestructure.adapters.output.MySQL.models.FacturaEntity;

@Mapper
public interface FacturaMapperOut {

  FacturaMapperOut INSTANCIA = Mappers.getMapper(FacturaMapperOut.class);

  /**
   * Mapea una clase de tipo entidad a clase de dominio
   * 
   * @param facturaEntity clase entidad
   * @return clase de dominio
   */
  Factura entityToBase(final FacturaEntity facturaEntity);

  /**
   * Mapea una clase de dominio a una de tipo entidad
   * 
   * @param factura clase de dominio
   * @return clase de tipo entidad
   */
  FacturaEntity baseToEntity(final Factura factura);

  /**
   * Mapea una clase de tipo entidad a clase de dominio
   * 
   * @param consecutivoFacturaEntity clase entidad
   * @return clase de dominio
   */
  ConsecutivoFactura entityToBase(final ConsecutivoFacturaEntity consecutivoFacturaEntity);

  /**
   * Mapea una clase de dominio a una de tipo entidad
   * 
   * @param consecutivoFactura clase de dominio
   * @return clase de tipo entidad
   */
  ConsecutivoFacturaEntity baseToEntity(final ConsecutivoFactura consecutivoFactura);

}
