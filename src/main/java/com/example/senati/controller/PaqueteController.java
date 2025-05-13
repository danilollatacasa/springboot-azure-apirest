package com.example.senati.controller;

import com.example.senati.model.entity.Paquete;
import com.example.senati.service.IPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class PaqueteController {

    @Autowired
    private IPaquete paqueteService;

    @GetMapping("/paquetes")
    public List<Paquete> getAll() {
        return paqueteService.getAll();
    }

    @GetMapping("paquetes/asignado/{codigoBarra}")
    public ResponseEntity<?> obtenerInfoPaquete(@PathVariable String codigoBarra) {
        Optional<Paquete> paqueteOptional = paqueteService.buscarPorCodigoBarra(codigoBarra);

        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            if (paquete.getAsignaciones() != null && !paquete.getAsignaciones().isEmpty()) {
                // Accede al usuario a través de la relación con UsuarioRepartidor
                if (paquete.getAsignaciones().get(0).getUsuarioRepartidor() != null &&
                        paquete.getAsignaciones().get(0).getUsuarioRepartidor().getUsuario() != null) {
                    String nombreRepartidor = paquete.getAsignaciones().get(0).getUsuarioRepartidor().getUsuario().getNombre();
                    return ResponseEntity.ok("Paquete encontrado. Asignado a: " + nombreRepartidor);
                } else {
                    return ResponseEntity.ok("Paquete encontrado. La asignación no tiene información del repartidor.");
                }
            } else {
                return ResponseEntity.ok("Paquete encontrado. Aún no asignado.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paquete no encontrado.");
        }
    }



    @GetMapping("/paquetes/{id}")
    public Paquete getById(@PathVariable Integer id) {
        return paqueteService.getById(id);
    }

    @PostMapping("/paquetes")
    public Paquete create(@RequestBody Paquete paquete) {
        return paqueteService.save(paquete);
    }

    @PutMapping("/paquetes/{id}")
    public Paquete update(@PathVariable Integer id, @RequestBody Paquete paquete) {
        return paqueteService.update(id, paquete);
    }


    @DeleteMapping("/paquetes/{id}")
    public void delete(@PathVariable Integer id) {
        Paquete paqueteDelete = paqueteService.getById(id);
        paqueteService.delete(paqueteDelete);
    }
}
