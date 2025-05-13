package com.example.senati.service;

import com.example.senati.model.entity.EstadoEntrega;

import java.util.List;
import java.util.Optional;

public interface IEstadoEntrega {


    List<EstadoEntrega> getAll();
    EstadoEntrega findById(Integer id);
    EstadoEntrega save(EstadoEntrega estadoEntrega);
    void delete(EstadoEntrega estadoEntrega);
    Optional<EstadoEntrega> findByDescripcion(String descripcion);
}
