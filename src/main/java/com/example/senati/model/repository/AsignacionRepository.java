package com.example.senati.model.repository;

import com.example.senati.model.entity.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsignacionRepository extends JpaRepository<Asignacion,Integer> {
    @Query("SELECT COUNT(a) > 0 FROM Asignacion a WHERE a.paquete.id = :paqueteId")
    boolean paqueteYaAsignado(@Param("paqueteId") Integer paqueteId);

}
