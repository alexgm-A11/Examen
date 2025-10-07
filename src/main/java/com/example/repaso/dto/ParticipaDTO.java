package com.example.repaso.dto;

import com.example.repaso.entity.Colaborador;
import com.example.repaso.entity.Proyecto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParticipaDTO {
    private Long idparticipa;
    private Long idproyecto;
    private Long idcolaborador;
}
