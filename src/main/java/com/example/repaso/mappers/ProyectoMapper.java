package com.example.repaso.mappers;

import com.example.repaso.dto.ProyectoDTO;
import com.example.repaso.entity.Proyecto;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProyectoMapper extends BaseMappers<Proyecto, ProyectoDTO> {
    @Mapping(target = "idcliente", source = "cliente.idcliente")
    ProyectoDTO toDTO(Proyecto proyecto);

    // Mapeo de DTO a Entity (ignora cliente porque se asigna manualmente en el servicio)
    @Mapping(target = "cliente", ignore = true)
    Proyecto toEntity(ProyectoDTO proyectoDTO);
}
