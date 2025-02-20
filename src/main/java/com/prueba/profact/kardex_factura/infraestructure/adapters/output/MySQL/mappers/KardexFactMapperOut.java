package com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.kardex_factura.infraestructure.adapters.output.MySQL.models.KardexFacturaEntity;

@Mapper
public interface KardexFactMapperOut {

  KardexFactMapperOut INSTANCIA = Mappers.getMapper(KardexFactMapperOut.class);

  /**
   * Mapea una clase de tipo entidad a clase de dominio
   * 
   * @param kardexFacturaEntity clase entidad
   * @return clase de dominio
   */
  KardexFactura entityToBase(final KardexFacturaEntity kardexFacturaEntity);

  /**
   * Mapea una clase de dominio a una de tipo entidad
   * 
   * @param kardexFactura clase de dominio
   * @return clase de tipo entidad
   */
  KardexFacturaEntity baseToEntity(final KardexFactura kardexFactura);

}
