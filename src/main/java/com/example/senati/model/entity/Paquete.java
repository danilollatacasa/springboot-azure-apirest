package com.example.senati.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_paquete")
public class Paquete {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    @Schema(description = "autogenerado", example = "-")
    private Integer id;

    @Column(name="producto")
    @Schema(description = "nombre de producto", example = "pelota")
    private String producto;

    @Column(name="remitente")
    @Schema(description = "persona que envía", example = "carlos")
    private String remitente;
    @Column(name="destinatario")
    @Schema(description = "persona que recibe", example = "carlos")
    private String destinatario;

    @Schema(description = "fecha", example = "-")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_llegada")
    private LocalDate fechaLlegada;

    @Schema(example = "-")
    @Column(name="codigo_barra")
    private String codigoBarra;

    @Schema(example = "-")
    @Column(name="codigo_barra_url")
    private String codigoBarraUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "paquete")
    private List<Asignacion> asignaciones;
}
