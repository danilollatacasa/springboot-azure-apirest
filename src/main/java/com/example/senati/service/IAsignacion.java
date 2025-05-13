package com.example.senati.service;

import com.example.senati.model.entity.Asignacion;
import com.example.senati.model.entity.Distrito;

import java.util.List;

public interface IAsignacion {
    List<Asignacion> getAll();
    Asignacion findById(Integer id);
    Asignacion save(Asignacion asignacion);
    void delete(Asignacion asignacion);
}
