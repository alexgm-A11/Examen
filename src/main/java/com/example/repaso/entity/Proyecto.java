package com.example.repaso.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "TBL_PROYECTO")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROYECTO")
    private Long idproyecto;

    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;

    @Column(name = "CUANTIA")
    private BigDecimal cuantia;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechainicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechafin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCLIENTE")
    private Cliente cliente;
}
