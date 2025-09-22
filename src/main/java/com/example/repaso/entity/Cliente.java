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
@Table(name="TBL_CLIENTES")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRES", unique = true, nullable = false, length = 45)
    private String nombres;
    @Column(name = "DIRECCION", unique = true, nullable = false, length = 45)
    private String direccion;
    @Column(name = "TELEFONO", unique = true, nullable = false, length = 9)
    private String telefono;
}
