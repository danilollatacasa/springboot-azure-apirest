package com.example.senati.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_entrega")
public class Entrega {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "direccion")
    private String  direccion;

    @Column(name = "numero_contacto")
    private String numeroContacto;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "dni_receptor")
    private String dniReceptor;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    @Column(name = "nombre_receptor")
    private String nombreReceptor;

    @ManyToOne
    @JoinColumn(name = "asignacion_id")
    private Asignacion  asignacion;

    @ManyToOne
    @JoinColumn(name = "motivo_rechazo_id")
    private MotivoRechazo motivoRechazo;

    @ManyToOne
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;

    @ManyToOne
    @JoinColumn(name = "estado_entrega_id")
    private EstadoEntrega estadoEntrega;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistorialFotos> historialFotos;

}
