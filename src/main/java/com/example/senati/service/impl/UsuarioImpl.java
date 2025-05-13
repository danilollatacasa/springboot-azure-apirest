package com.example.senati.service.impl;

import com.example.senati.model.entity.Usuario;
import com.example.senati.model.entity.UsuarioAdministrador;
import com.example.senati.model.entity.UsuarioRepartidor;
import com.example.senati.model.repository.UsuarioAdministradorRepository;
import com.example.senati.model.repository.UsuarioRepartidorRepository;
import com.example.senati.model.repository.UsuarioRepository;
import com.example.senati.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioImpl implements IUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRepartidorRepository usuarioRepartidorRepository;

    @Autowired
    private UsuarioAdministradorRepository usuarioAdministradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario getById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        // Encriptar solo si no está encriptada
        String contrasena = usuario.getContrasena();
        if (contrasena != null && !contrasena.startsWith("$2a$")) {
            String contrasenaEncriptada = passwordEncoder.encode(contrasena);
            usuario.setContrasena(contrasenaEncriptada);
        }

        // Guardar usuario
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Eliminar registros previos en subtablas
        usuarioAdministradorRepository.deleteByUsuarioId(usuarioGuardado.getId());
        usuarioRepartidorRepository.deleteByUsuarioId(usuarioGuardado.getId());

        // Insertar en la subtabla correspondiente al nuevo rol
        Integer idRol = usuario.getRol().getId();
        if (idRol == 1) {
            UsuarioAdministrador admin = new UsuarioAdministrador();
            admin.setUsuario(usuarioGuardado);
            usuarioAdministradorRepository.save(admin);
        } else if (idRol == 2) {
            UsuarioRepartidor repartidor = new UsuarioRepartidor();
            repartidor.setUsuario(usuarioGuardado);
            usuarioRepartidorRepository.save(repartidor);
        }

        return usuarioGuardado;
    }





    @Transactional
    @Override
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

}
