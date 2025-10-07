package com.example.repaso.mappers;

import com.example.repaso.dto.ColaboradorDTO;
import com.example.repaso.entity.Colaborador;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends BaseMappers<Colaborador, ColaboradorDTO> {
}
