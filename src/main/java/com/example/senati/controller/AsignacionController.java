package com.example.senati.controller;

import com.example.senati.model.entity.Asignacion;
import com.example.senati.service.IAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AsignacionController {

    @Autowired
    private IAsignacion asignacionService;

    @GetMapping("/asignaciones")
    public List<Asignacion> getAll() {
        return asignacionService.getAll();
    };

    @GetMapping("/asignaciones/{id}")
    public Asignacion getById(@PathVariable Integer id) {
        return asignacionService.findById(id);
    }

    @PostMapping("/asignaciones")
    public Asignacion create(@RequestBody Asignacion asignacion) {
        return asignacionService.save(asignacion);
    }

    @PutMapping("/asignaciones/{id}")
    public Asignacion update(@PathVariable Integer id, @RequestBody Asignacion asignacion) {
        asignacion.setId(id);
        return asignacionService.save(asignacion);
    }

    @DeleteMapping("/asignaciones/{id}")
    public void delete(@PathVariable Integer id){
        Asignacion asignacionDelete = asignacionService.findById(id);
        asignacionService.delete(asignacionDelete);
    }


}
