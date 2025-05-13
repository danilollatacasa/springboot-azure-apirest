package com.example.senati.model.repository;

import com.example.senati.model.entity.EstadoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadoEntregaRepository extends JpaRepository<EstadoEntrega,Integer> {
    @Query("SELECT e FROM EstadoEntrega e WHERE LOWER(e.descripcion) = LOWER(:descripcion)")
    Optional<EstadoEntrega> findByDescripcionIgnoreCase(@Param("descripcion") String descripcion);


}
