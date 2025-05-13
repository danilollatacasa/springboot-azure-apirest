package com.example.senati.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="tb_asignacion")
public class Asignacion {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @ManyToOne
    @JoinColumn(name = "id_repartidor")
    private UsuarioRepartidor usuarioRepartidor;

    @ManyToOne
    @JoinColumn(name = "id_paquete")
    private Paquete paquete;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private UsuarioAdministrador usuarioAdministrador;
}
