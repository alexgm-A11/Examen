package com.example.repaso.service.impl;

import com.example.repaso.controller.exceptions.ResourceNotFoundException;
import com.example.repaso.dto.ProyectoDTO;
import com.example.repaso.entity.Cliente;
import com.example.repaso.entity.Proyecto;
import com.example.repaso.mappers.ProyectoMapper;
import com.example.repaso.repository.ClienteRepository;
import com.example.repaso.repository.ProyectoRepository;
import com.example.repaso.service.service.ProyectoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProyectoServiceImpl implements ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;
    private final ClienteRepository clienteRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, ProyectoMapper proyectoMapper, ClienteRepository clienteRepository) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ProyectoDTO create(ProyectoDTO proyectoDTO) throws ServiceException {
        try {
            Cliente cliente = clienteRepository.findById(proyectoDTO.getIdcliente()).orElseThrow(() -> new ServiceException("No existe el cliente con id: " + proyectoDTO.getIdcliente()));
            Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
            proyecto.setCliente(cliente);
            Proyecto proyectoSaved = proyectoRepository.save(proyecto);
            return proyectoMapper.toDTO(proyectoSaved);
        } catch(Exception e) {
            throw new ServiceException("Error al crear Proyecto: " + e.getMessage());
        }
    }

    @Override
    public ProyectoDTO update(Long aLong, ProyectoDTO proyectoDTO) throws ServiceException {
        try {
            Proyecto proyecto = proyectoRepository.findById(aLong)
                    .orElseThrow(() -> new ServiceException("No existe el proyecto"));

            // Actualizar campos básicos
            proyecto.setDescripcion(proyectoDTO.getDescripcion());
            proyecto.setCuantia(proyectoDTO.getCuantia());
            proyecto.setFechainicio(proyectoDTO.getFechainicio());
            proyecto.setFechafin(proyectoDTO.getFechafin());

            // Si se actualiza el cliente
            if (proyectoDTO.getIdcliente() != null) {
                Cliente cliente = clienteRepository.findById(proyectoDTO.getIdcliente())
                        .orElseThrow(() -> new ServiceException("No existe el cliente con id: " + proyectoDTO.getIdcliente()));
                proyecto.setCliente(cliente);
            }

            return proyectoMapper.toDTO(proyectoRepository.save(proyecto));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el proyecto: " + e);
        }
    }

    @Override
    public ProyectoDTO findById(Long aLong) throws ServiceException {
        try {
            Proyecto proyecto = proyectoRepository.findById(aLong)
                    .orElseThrow(() -> new ServiceException("No existe el proyecto"));
            return proyectoMapper.toDTO(proyecto);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el proyecto con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!proyectoRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe el proyecto");
            }
            proyectoRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el proyecto con id " + aLong, e);
        }
    }

    @Override
    public List<ProyectoDTO> findAll() throws ServiceException {
        try {
            return proyectoMapper.toDTOs(proyectoRepository.findAll());
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los proyectos: " + e);
        }
    }
}
