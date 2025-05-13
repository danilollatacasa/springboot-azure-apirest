package com.example.senati.model.repository;

import com.example.senati.model.entity.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaqueteRepository extends JpaRepository<Paquete,Integer> {
    @Query("SELECT p FROM Paquete p WHERE p.codigoBarra = :codigoBarra")
    Paquete findByCodigoBarra(@Param("codigoBarra") String codigoBarra);

    @Query("SELECT p FROM Paquete p LEFT JOIN FETCH p.asignaciones a LEFT JOIN FETCH a.usuarioRepartidor WHERE p.codigoBarra = :codigoBarra")
    Optional<Paquete> buscarPaqueteConRepartidor(@Param("codigoBarra") String codigoBarra);

}
