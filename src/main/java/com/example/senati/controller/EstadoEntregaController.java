package com.example.senati.controller;

import com.example.senati.model.entity.EstadoEntrega;
import com.example.senati.service.IEstadoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EstadoEntregaController {

    @Autowired
    private IEstadoEntrega estadoEntregaService;

    @GetMapping("/estadoentregas")
    public List<EstadoEntrega> getAll(){
        return estadoEntregaService.getAll();
    }

    @GetMapping("/estadoentregas/{id}")
    public EstadoEntrega getById(@PathVariable Integer id) {
        return estadoEntregaService.findById(id);
    }

    @PostMapping("/estadoentregas")
    public EstadoEntrega create(@RequestBody EstadoEntrega estadoEntrega){
        return estadoEntregaService.save(estadoEntrega);
    }

    @PutMapping("/estadoentregas/{id}")
    public EstadoEntrega save(@PathVariable Integer id, @RequestBody EstadoEntrega estadoEntrega){
        estadoEntrega.setId(id);
        return estadoEntregaService.save(estadoEntrega);
    }

    @DeleteMapping("estadoentregas/{id}")
    public void delete(@PathVariable Integer id){
        EstadoEntrega estadoEntregaDelete = estadoEntregaService.findById(id);
        estadoEntregaService.delete(estadoEntregaDelete);
    }

}
