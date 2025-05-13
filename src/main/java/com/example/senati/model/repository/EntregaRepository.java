package com.example.senati.model.repository;

import com.example.senati.model.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega,Integer> {
    @Query("SELECT e FROM Entrega e WHERE e.asignacion.paquete.codigoBarra = :codigoBarra")
    Entrega findByCodigoBarraPaquete(@Param("codigoBarra") String codigoBarra);



}
