package com.example.repaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleDTO {
    private Long id;
    private Long ventaId;
    private Long productoId;
    private BigDecimal precio;
}
