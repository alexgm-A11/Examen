package com.example.repaso.service.impl;

import com.example.repaso.controller.exceptions.ResourceNotFoundException;
import com.example.repaso.controller.exceptions.ResourceValidationException;
import com.example.repaso.dto.DetalleDTO;
import com.example.repaso.entity.Detalle;
import com.example.repaso.entity.Producto;
import com.example.repaso.mappers.DetalleMapper;
import com.example.repaso.repository.DetalleRepository;
import com.example.repaso.repository.ProductoRepository;
import com.example.repaso.service.service.DetalleService;
import org.hibernate.service.spi.ServiceException;
 import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class DetalleServiceImpl implements DetalleService {
    private final DetalleRepository detalleRepository;
    private final DetalleMapper detalleMapper;
    private final ProductoRepository productoRepository;

    public DetalleServiceImpl(DetalleRepository detalleRepository, DetalleMapper detalleMapper, ProductoRepository productoRepository) {
        this.detalleRepository = detalleRepository;
        this.detalleMapper = detalleMapper;
        this.productoRepository = productoRepository;
    }

    @Override
    public DetalleDTO create(DetalleDTO detalleDTO) throws ServiceException {
        if(detalleDTO==null){
           throw new IllegalArgumentException("El detalle no puede ser nulo.");
        }
        if(detalleDTO.getPrecio()==null || detalleDTO.getPrecio().compareTo(BigDecimal.ZERO)<=0){
           throw new ResourceValidationException("El precio unitario debe ser mayor que 0");
        }
        try {
            Detalle detalle = detalleMapper.toEntity(detalleDTO);
            return detalleMapper.toDTO(detalleRepository.save(detalle));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DetalleDTO update(Long aLong, DetalleDTO detalleDTO) throws ServiceException {

        if (aLong == null || detalleDTO == null) {
            throw new IllegalArgumentException("El ID y el detalle no pueden ser nulos");
        }
        Detalle existente = detalleRepository.findById(aLong)
             .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + aLong));

        if (detalleDTO.getPrecio() == null || detalleDTO.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResourceValidationException("El precio debe ser mayor que 0");
        }
        try {
            existente.setPrecio(detalleDTO.getPrecio());
            if(detalleDTO.getProductoId()!=null){
                Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                        .orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con ID "+aLong));
                existente.setProducto(producto);
            }
            Detalle actualizado = detalleRepository.save(existente);
            return detalleMapper.toDTO(actualizado);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DetalleDTO findById(Long aLong) throws ServiceException {
         if (aLong == null) {
             throw new IllegalArgumentException("El ID no puede ser nulos");
         }
         Detalle existente = detalleRepository.findById(aLong)
            .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + aLong));
        try {
            return detalleMapper.toDTO(existente);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
                throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Detalle existente = detalleRepository.findById(aLong)
           .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + aLong));
        try{
            detalleRepository.delete(existente);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DetalleDTO> findAll() throws ServiceException {
        List<Detalle> detalles = detalleRepository.findAll();
        if (detalles.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron detalles registrados");
        }

        return detalles.stream()
                .map(detalleMapper::toDTO)
                .toList();
    }
}
