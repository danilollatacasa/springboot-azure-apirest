package com.example.senati.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.aot.generate.GenerationContext;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="tb_historial_fotos")
public class HistorialFotos {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="url_foto")
    private String urlFoto;

    @ManyToOne
    @JoinColumn(name="entrega_id")
    private Entrega entrega;

}
