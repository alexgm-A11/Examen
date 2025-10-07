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
@Table(name = "TBL_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCLIENTE")
    private Long idcliente;
    @Column(name = "TELEFONO", length = 15)
    private String telefono;
    @Column(name = "DOMICILIO", length = 150)
    private String domicilio;
    @Column(name = "RAZONSOCIAL", length = 200)
    private String razonsocial;
}
