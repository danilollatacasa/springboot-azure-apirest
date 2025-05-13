package com.example.senati.service.impl;

import com.example.senati.model.entity.Asignacion;
import com.example.senati.model.repository.AsignacionRepository;
import com.example.senati.service.IAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsignacionImpl implements IAsignacion {

    @Autowired
    AsignacionRepository asignacionRepository;

    @Override
    public List<Asignacion> getAll() {
        return asignacionRepository.findAll();
    };

    @Override
    public Asignacion findById(Integer id) {
        return asignacionRepository.findById(id).orElse(null);
    };

    @Override
    public Asignacion save(Asignacion asignacion) {
        Integer paqueteId = asignacion.getPaquete().getId();
        asignacion.setFechaAsignacion(LocalDate.now());


        // Validar si ya fue asignado
        if (asignacionRepository.paqueteYaAsignado(paqueteId)) {
            throw new IllegalStateException("El paquete ya ha sido asignado.");
        }

        return asignacionRepository.save(asignacion);
    }


    @Override
    public void delete(Asignacion asignacion) {
        asignacionRepository.delete(asignacion);
    }








}
