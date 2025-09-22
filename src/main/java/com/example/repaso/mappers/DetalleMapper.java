package com.example.repaso.mappers;

import com.example.repaso.dto.DetalleDTO;
import com.example.repaso.entity.Detalle;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleMapper extends BaseMappers<Detalle, DetalleDTO> {
    @Mapping(source = "venta.id", target = "ventaId")
    @Mapping(source = "producto.id", target = "productoId")
    DetalleDTO toDTO(Detalle detalle);

    @InheritInverseConfiguration
    Detalle toEntity(DetalleDTO detalleDTO);
}
