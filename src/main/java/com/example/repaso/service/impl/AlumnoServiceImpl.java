package com.example.repaso.service.impl;

import com.example.repaso.dto.AlumnoDTO;
import com.example.repaso.entity.Alumno;
import com.example.repaso.mappers.AlumnoMapper;
import com.example.repaso.repository.AlumnoRepository;
import com.example.repaso.service.service.AlumnoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    @Override
    public AlumnoDTO create(AlumnoDTO alumnoDTO) throws ServiceException {
        try {
            Alumno alumno = alumnoMapper.toEntity(alumnoDTO);
            Alumno alumnoSaved = alumnoRepository.save(alumno);
            return alumnoMapper.toDTO(alumnoSaved);
        } catch (Exception e) {
            throw new ServiceException("Error al crear Categoría",e);
        }
    }

    @Override
    public AlumnoDTO update(Long aLong, AlumnoDTO alumnoDTO) throws ServiceException {
        return null;
    }

    @Override
    public AlumnoDTO findById(Long aLong) throws ServiceException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {

    }

    @Override
    public List<AlumnoDTO> findAll() throws ServiceException {
        try {
            List<Alumno> alumnos = alumnoRepository.findAll();
            return alumnoMapper.toDTOs(alumnos);
        }catch (Exception e) {
            throw new ServiceException("Error al listar las categorías",e);
        }
    }
}
