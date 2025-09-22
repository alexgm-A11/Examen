package com.example.repaso.mappers;

import com.example.repaso.dto.VentaDTO;
import com.example.repaso.entity.Venta;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetalleMapper.class})
public interface VentaMapper extends BaseMappers<Venta, VentaDTO> {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "detalles", target = "detalles")
    VentaDTO toDTO(Venta venta);

    @InheritInverseConfiguration
    Venta toEntity(VentaDTO ventaDTO);
}
