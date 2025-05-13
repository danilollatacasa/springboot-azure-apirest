package com.example.senati.model.repository;

import com.example.senati.model.entity.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioAdministradorRepository extends JpaRepository<UsuarioAdministrador,Integer> {
    void deleteByUsuarioId(Integer usuarioId);
    boolean existsByUsuarioId(Integer usuarioId);
}
