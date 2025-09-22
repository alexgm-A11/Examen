package com.example.repaso.service.impl;

import com.example.repaso.controller.exceptions.ResourceNotFoundException;
import com.example.repaso.controller.exceptions.ResourceValidationException;
import com.example.repaso.dto.DetalleDTO;
import com.example.repaso.dto.VentaDTO;
import com.example.repaso.entity.Cliente;
import com.example.repaso.entity.Detalle;
import com.example.repaso.entity.Producto;
import com.example.repaso.entity.Venta;
import com.example.repaso.mappers.VentaMapper;
import com.example.repaso.repository.ClienteRepository;
import com.example.repaso.repository.ProductoRepository;
import com.example.repaso.repository.VentaRepository;
import com.example.repaso.service.service.VentaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, VentaMapper ventaMapper, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }
    @Transactional
    @Override
    public VentaDTO create(VentaDTO ventaDTO) throws ServiceException {
        if (ventaDTO == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        Cliente cliente = clienteRepository.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + ventaDTO.getClienteId()));

        if (ventaDTO.getDetalles()== null || ventaDTO.getDetalles().isEmpty()) {
            throw new ResourceValidationException("La venta debe tener al menos un detalle");
        }
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente);

        List<Detalle> detalles = new ArrayList<>();
        for (DetalleDTO d : ventaDTO.getDetalles()) {
            if (d.getPrecio() == null || d.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResourceValidationException("El precio debe ser mayor que 0");
            }
            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + d.getProductoId()));

            Detalle detalle = new Detalle();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setPrecio(d.getPrecio());

            detalles.add(detalle);
        }
        venta.setDetalles(detalles);
        Venta guardada = ventaRepository.save(venta);
        return ventaMapper.toDTO(guardada);
   }
    @Transactional
    @Override
    public VentaDTO update(Long aLong, VentaDTO ventaDTO) throws ServiceException {
        if (aLong == null || ventaDTO == null) {
            throw new IllegalArgumentException("El ID y la venta no pueden ser nulos");
        }
        Venta ventaExistente = ventaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + aLong));

        Cliente cliente = clienteRepository.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + ventaDTO.getClienteId()));

        if (ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()) {
            throw new ResourceValidationException("La venta debe tener al menos un detalle");
        }
        ventaExistente.setFecha(ventaDTO.getFecha() != null ? ventaDTO.getFecha() : ventaExistente.getFecha());
        ventaExistente.setCliente(cliente);
        ventaExistente.getDetalles().clear();
        List<Detalle> nuevosDetalles = new ArrayList<>();

        for (DetalleDTO d : ventaDTO.getDetalles()) {

            if (d.getPrecio() == null || d.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResourceValidationException("El precio debe ser mayor que 0");
            }

            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + d.getProductoId()));

            Detalle detalle = new Detalle();
            detalle.setVenta(ventaExistente);
            detalle.setProducto(producto);
            detalle.setPrecio(d.getPrecio());

            nuevosDetalles.add(detalle);
        }

        ventaExistente.setDetalles(nuevosDetalles);
        Venta actualizada = ventaRepository.save(ventaExistente);
        return ventaMapper.toDTO(actualizada);
    }
    @Transactional(readOnly = true)
    @Override
    public VentaDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        Venta venta = ventaRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + aLong));

        return ventaMapper.toDTO(venta);
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!ventaRepository.existsById(aLong)) {
            throw new ResourceNotFoundException("Venta no encontrada con ID: " + aLong);
        }

        try {
            ventaRepository.deleteById(aLong);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar la venta porque tiene detalles asociados", ex);
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<VentaDTO> findAll() throws ServiceException {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream()
                .map(ventaMapper::toDTO)
                .toList();
    }
}
