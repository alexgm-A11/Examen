package com.example.repaso.mappers;

import com.example.repaso.dto.ParticipaDTO;
import com.example.repaso.entity.Participa;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipaMapper extends BaseMappers<Participa, ParticipaDTO> {
}
