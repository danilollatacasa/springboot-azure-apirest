package com.example.senati.service.impl;

import com.example.senati.model.entity.Jornada;
import com.example.senati.model.entity.Rol;
import com.example.senati.model.entity.Usuario;
import com.example.senati.model.repository.JornadaRepository;
import com.example.senati.model.repository.UsuarioRepartidorRepository;
import com.example.senati.service.IJornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class JornadaImpl implements IJornada {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private UsuarioRepartidorRepository usuarioRepartidorRepository;

    @Override
    public Jornada iniciarJornada(Jornada jornada) {
        Usuario usuario = jornada.getUsuarioRepartidor().getUsuario();

        boolean esRepartidor = usuarioRepartidorRepository.existsByUsuarioId(usuario.getId());

        if (!esRepartidor) {
            throw new RuntimeException("Solo un repartidor puede iniciar jornada.");
        }

        jornada.setFecha(LocalDate.now());
        jornada.setHoraInicio(LocalTime.now());
        jornada.setHoraFin(null);

        return jornadaRepository.save(jornada);
    }

    @Override
    public List<Jornada> listarJornadas() {
        return jornadaRepository.findAll();
    }

    @Override
    public Jornada finalizarJornada(Integer id) {
        Jornada jornada = jornadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        if (jornada.getHoraFin() != null) {
            throw new RuntimeException("La jornada ya fue finalizada");
        }

        jornada.setHoraFin(LocalTime.now());
        return jornadaRepository.save(jornada);
    }

    @Override
    public Jornada getJornadaById(Integer id) {
        return jornadaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarJornada(Jornada jornada) {
        jornadaRepository.delete(jornada);
    }
}