package com.example.repaso.mappers;

import com.example.repaso.dto.ClienteDTO;
import com.example.repaso.entity.Cliente;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMappers<Cliente, ClienteDTO> {
}
