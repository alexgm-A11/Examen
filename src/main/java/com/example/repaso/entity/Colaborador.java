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
@Table(name = "TBL_COLABORADOR")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCOLABORADOR")
    private Long idcolaborador;
    @Column(name = "NIF", length = 15)
    private String nif;
    @Column(name = "NOMBRE", length = 100)
    private String nombre;
    @Column(name = "DOMICILIO", length = 150)
    private String domicilio;
    @Column(name = "TELEFONO", length = 15)
    private String telefono;
    @Column(name = "BANCO", length = 100)
    private String banco;
    @Column(name = "NUM_CUENTA", length = 30)
    private String numcuenta;
}
