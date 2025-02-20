package com.prueba.profact.kardex_factura.domain.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.profact.kardex_factura.domain.models.KardexFactura;
import com.prueba.profact.shared.infraestructure.adapters.input.rest.models.request.SharedKardexFacturaDTO;

@Mapper
public interface KardexFacturaDomainMapper {

  final KardexFacturaDomainMapper INSTANCIA = Mappers.getMapper(KardexFacturaDomainMapper.class);

  /**
   * Mapea una clase compartida a clase de dominio
   * 
   * @param sharedKardexFacturaDTO clase compartida
   * @return clase de dominio
   */
  @Mapping(target = "krdxId", ignore = true)
  KardexFactura sharedDtoToBase(final SharedKardexFacturaDTO sharedKardexFacturaDTO);

  /**
   * Mapea una clase de dominio a clase compartida
   * 
   * @param kardexFactura clase de dominio
   * @return clase compartida
   */
  SharedKardexFacturaDTO baseToSharedDto(final KardexFactura kardexFactura);

  /**
   * Mapea una lista de clases compartidas a clases de dominio
   * 
   * @param sharedKardexFacturaDTO clases compartidas
   * @return clases de dominio
   */
  List<KardexFactura> sharedDtoToBase(final List<SharedKardexFacturaDTO> sharedKardexFacturaDTO);

  /**
   * Mapea una lista de clases de dominio a clases compartidas
   * 
   * @param kardexFactura clases de dominio
   * @return clases compartidas
   */
  List<SharedKardexFacturaDTO> baseToSharedDto(final List<KardexFactura> kardexFactura);

}
