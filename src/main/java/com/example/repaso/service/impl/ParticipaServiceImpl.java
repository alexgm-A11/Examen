package com.example.repaso.service.impl;

import com.example.repaso.controller.exceptions.ResourceNotFoundException;
import com.example.repaso.controller.exceptions.ResourceValidationException;
import com.example.repaso.dto.ParticipaDTO;
import com.example.repaso.entity.Colaborador;
import com.example.repaso.entity.Participa;
import com.example.repaso.entity.Proyecto;
import com.example.repaso.mappers.ParticipaMapper;
import com.example.repaso.repository.ColaboradorRepository;
import com.example.repaso.repository.ParticipaRepository;
import com.example.repaso.repository.ProyectoRepository;
import com.example.repaso.service.service.ParticipaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipaServiceImpl implements ParticipaService {
    private final ParticipaRepository participaRepository;
    private final ParticipaMapper participaMapper;
    private final ColaboradorRepository colaboradorRepository;
    private final ProyectoRepository proyectoRepository;

    public ParticipaServiceImpl(ParticipaRepository participaRepository, ParticipaMapper participaMapper, ColaboradorRepository colaboradorRepository, ProyectoRepository proyectoRepository) {
        this.participaRepository = participaRepository;
        this.participaMapper = participaMapper;
        this.colaboradorRepository = colaboradorRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public ParticipaDTO create(ParticipaDTO participaDTO) throws ServiceException {
        try {
            // Verificar que el proyecto existe
            Proyecto proyecto = proyectoRepository.findById(participaDTO.getIdproyecto())
                    .orElseThrow(() -> new ServiceException("No existe el proyecto con id: " + participaDTO.getIdproyecto()));

            // Verificar que el colaborador existe
            Colaborador colaborador = colaboradorRepository.findById(participaDTO.getIdcolaborador())
                    .orElseThrow(() -> new ServiceException("No existe el colaborador con id: " + participaDTO.getIdcolaborador()));

            // Convertir DTO a Entity y asignar las relaciones
            Participa participa = participaMapper.toEntity(participaDTO);
            participa.setProyecto(proyecto);
            participa.setColaborador(colaborador);

            Participa participaSaved = participaRepository.save(participa);
            return participaMapper.toDTO(participaSaved);
        } catch(Exception e) {
            throw new ServiceException("Error al crear Participación: " + e.getMessage());
        }
    }

    @Override
    public ParticipaDTO update(Long aLong, ParticipaDTO participaDTO) throws ServiceException {
        try {
            Participa participa = participaRepository.findById(aLong)
                    .orElseThrow(() -> new ServiceException("No existe la participación"));

            // Si se actualiza el proyecto
            if (participaDTO.getIdproyecto() != null) {
                Proyecto proyecto = proyectoRepository.findById(participaDTO.getIdproyecto())
                        .orElseThrow(() -> new ServiceException("No existe el proyecto con id: " + participaDTO.getIdproyecto()));
                participa.setProyecto(proyecto);
            }

            // Si se actualiza el colaborador
            if (participaDTO.getIdcolaborador() != null) {
                Colaborador colaborador = colaboradorRepository.findById(participaDTO.getIdcolaborador())
                        .orElseThrow(() -> new ServiceException("No existe el colaborador con id: " + participaDTO.getIdcolaborador()));
                participa.setColaborador(colaborador);
            }

            return participaMapper.toDTO(participaRepository.save(participa));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la participación: " + e);
        }
    }

    @Override
    public ParticipaDTO findById(Long aLong) throws ServiceException {
        try {
            Participa participa = participaRepository.findById(aLong)
                    .orElseThrow(() -> new ServiceException("No existe la participación"));
            return participaMapper.toDTO(participa);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer la participación con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!participaRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe la participación");
            }
            participaRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la participación con id " + aLong, e);
        }
    }

    @Override
    public List<ParticipaDTO> findAll() throws ServiceException {
        try {
            return participaMapper.toDTOs(participaRepository.findAll());
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar las participaciones: " + e);
        }
    }
}
