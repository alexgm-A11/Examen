package com.example.repaso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlumnoDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
}
