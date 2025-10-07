package com.example.repaso.dto;

import com.example.repaso.entity.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoDTO {
    private Long idproyecto;
    private String descripcion;
    private BigDecimal cuantia;
    private LocalDate fechainicio;
    private LocalDate fechafin;
    private Long idcliente;
}
