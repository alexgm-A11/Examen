package com.example.repaso.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="ALUMNO")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRES", unique = true, nullable = false, length = 45)
    private String nombres;
    @Column(name = "APELLIDOS", unique = true, nullable = false, length = 45)
    private String apellidos;
    @Column(name = "CORREO", unique = true, nullable = false, length = 45)
    private String correo;

}
