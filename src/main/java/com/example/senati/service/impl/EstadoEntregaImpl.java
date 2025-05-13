package com.example.senati.service.impl;

import com.example.senati.model.repository.EstadoEntregaRepository;
import com.example.senati.model.entity.EstadoEntrega;
import com.example.senati.service.IEstadoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoEntregaImpl implements IEstadoEntrega {

    @Autowired
    private EstadoEntregaRepository estadoEntregaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EstadoEntrega> getAll() {
        return estadoEntregaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoEntrega findById(Integer id){
        return estadoEntregaRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public EstadoEntrega save(EstadoEntrega estadoEntrega){
        return estadoEntregaRepository.save(estadoEntrega);
    }

    @Transactional
    @Override
    public void delete(EstadoEntrega estadoEntrega){
        estadoEntregaRepository.delete(estadoEntrega);
    }

    @Override
    public Optional<EstadoEntrega> findByDescripcion(String descripcion) {
        return estadoEntregaRepository.findByDescripcionIgnoreCase(descripcion);
    }
}
