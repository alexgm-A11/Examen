package com.example.repaso.mappers;

import com.example.repaso.dto.AlumnoDTO;
import com.example.repaso.entity.Alumno;
import com.example.repaso.mappers.base.BaseMappers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlumnoMapper extends BaseMappers<Alumno, AlumnoDTO> {
}
