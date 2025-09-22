package com.example.repaso.dto;

import com.example.repaso.entity.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaDTO
{
    private Long id;
    private LocalDateTime fecha;
    private Long clienteId;
    private List<DetalleDTO> detalles;
}
