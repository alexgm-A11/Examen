package com.example.repaso.service.impl;

import com.example.repaso.controller.exceptions.ResourceNotFoundException;
import com.example.repaso.dto.ColaboradorDTO;
import com.example.repaso.entity.Cliente;
import com.example.repaso.entity.Colaborador;
import com.example.repaso.mappers.ColaboradorMapper;
import com.example.repaso.repository.ColaboradorRepository;
import com.example.repaso.service.service.ColaboradorService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColaboradorServiceImpl implements ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final ColaboradorMapper colaboradorMapper;

    public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository, ColaboradorMapper colaboradorMapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.colaboradorMapper = colaboradorMapper;
    }

    @Override
    public ColaboradorDTO create(ColaboradorDTO colaboradorDTO) throws ServiceException {
        try {
            Colaborador colaborador = colaboradorMapper.toEntity(colaboradorDTO);
            Colaborador colaboradorSaved = colaboradorRepository.save(colaborador);
            return colaboradorMapper.toDTO(colaboradorSaved);
        }catch(Exception e) {
            throw new ServiceException("Error al crear Colaborador: "+e.getMessage());
        }
    }

    @Override
    public ColaboradorDTO update(Long aLong, ColaboradorDTO colaboradorDTO) throws ServiceException {
        try {
            Colaborador colaborador = colaboradorRepository.findById(aLong)
                    .orElseThrow(() -> new ServiceException("No existe el colaborador"));

            colaborador.setNif(colaboradorDTO.getNif());
            colaborador.setNombre(colaboradorDTO.getNombre());
            colaborador.setDomicilio(colaboradorDTO.getDomicilio());
            colaborador.setTelefono(colaboradorDTO.getTelefono());
            colaborador.setBanco(colaboradorDTO.getBanco());
            colaborador.setNumcuenta(colaboradorDTO.getNumcuenta());
            return colaboradorMapper.toDTO(colaboradorRepository.save(colaborador));
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el colaborador: " + e);
        }
    }

    @Override
    public ColaboradorDTO findById(Long aLong) throws ServiceException {
        try {
            Colaborador colaborador = colaboradorRepository.findById(aLong).orElseThrow(() -> new ServiceException("No existe el colaborador"));
            return colaboradorMapper.toDTO(colaborador);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al leer el colaborador con id " + aLong, e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        try {
            if(!colaboradorRepository.findById(aLong).isPresent()){
                throw new ServiceException("No existe el colaborador");
            }
            colaboradorRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el colaborador con id " + aLong, e);
        }
    }

    @Override
    public List<ColaboradorDTO> findAll() throws ServiceException {
        try {
            return colaboradorMapper.toDTOs(colaboradorRepository.findAll());
        } catch (ResourceNotFoundException e) {
            throw (e);
        } catch (Exception e) {
            throw new ServiceException("Error al listar los colaboradores: " + e);
        }
    }
}
