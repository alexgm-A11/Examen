package com.example.repaso.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ColaboradorDTO {
    private Long idcolaborador;
    private String nif;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String banco;
    private String numcuenta;
}
