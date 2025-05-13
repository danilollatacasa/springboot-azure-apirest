package com.example.senati.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_usuario_repartidor")
public class UsuarioRepartidor {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne
    @JoinColumn(name = "id_usuario") // FK que también es PK
    @MapsId // indica que este objeto usa el mismo ID que el de Usuario
    private Usuario usuario;

    // Getters y setters
}
