package com.example.senati.service;

import com.example.senati.model.entity.Distrito;
import com.example.senati.model.entity.Entrega;
import com.example.senati.model.entity.EstadoEntrega;

import java.util.List;
import java.util.Optional;

public interface IEntrega {
    List<Entrega> getAll();
    Entrega findById(Integer id);
    Entrega save(Entrega entrega);
    void delete(Entrega entrega);
    Entrega buscarPorCodigoBarraPaquete(String codigoBarra);
    Entrega actualizarEstadoEntrega(Integer entregaId, Integer nuevoEstadoId);

}
