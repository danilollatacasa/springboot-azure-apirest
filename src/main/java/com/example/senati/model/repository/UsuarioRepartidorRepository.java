package com.example.senati.model.repository;

import com.example.senati.model.entity.UsuarioRepartidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepartidorRepository extends JpaRepository<UsuarioRepartidor,Integer> {
    boolean existsByUsuarioId(Integer usuarioId);
    void deleteByUsuarioId(Integer usuarioId);

}
