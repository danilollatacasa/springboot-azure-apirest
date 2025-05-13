package com.example.senati.service.impl;

import com.example.senati.model.entity.Entrega;
import com.example.senati.model.entity.EstadoEntrega;
import com.example.senati.model.repository.EntregaRepository;
import com.example.senati.model.repository.EstadoEntregaRepository;
import com.example.senati.service.IEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaImpl  implements IEntrega {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EstadoEntregaRepository estadoEntregaRepository;

    @Override
    public List<Entrega> getAll() {
        return entregaRepository.findAll();
    }

    @Override
    public Entrega findById(Integer id) {
        return entregaRepository.findById(id).orElse(null);
    }

    @Override
    public Entrega save(Entrega entrega) {
        // Buscar el estado "Pendiente" por su descripción, ignorando mayúsculas y minúsculas
        EstadoEntrega estadoPendiente = estadoEntregaRepository.findByDescripcionIgnoreCase("Pendiente")
                .orElseThrow(() -> new RuntimeException("Estado 'Pendiente' no encontrado"));

        // Asignar el estado "Pendiente" a la entrega
        entrega.setEstadoEntrega(estadoPendiente);

        // Guardar la entrega con el estado "Pendiente"
        return entregaRepository.save(entrega);
    }
    @Override
    public Entrega actualizarEstadoEntrega(Integer entregaId, Integer nuevoEstadoId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        String estadoActual = entrega.getEstadoEntrega().getDescripcion().toLowerCase();

        if (!estadoActual.equals("pendiente")) {
            throw new RuntimeException("Solo se puede cambiar el estado si está en Pendiente");
        }

        EstadoEntrega nuevoEstado = estadoEntregaRepository.findById(nuevoEstadoId)
                .orElseThrow(() -> new RuntimeException("Nuevo estado no es válido"));

        entrega.setEstadoEntrega(nuevoEstado);
        return entregaRepository.save(entrega);
    }


    @Override
    public Entrega buscarPorCodigoBarraPaquete(String codigoBarra) {
        Entrega entrega = entregaRepository.findByCodigoBarraPaquete(codigoBarra);
        if (entrega == null) {
            throw new RuntimeException("Entrega no encontrada para el código de barra: " + codigoBarra);
        }
        return entrega;
    }


    @Override
    public void delete(Entrega entrega) {
        entregaRepository.delete(entrega);
    }
}
