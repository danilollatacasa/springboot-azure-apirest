package com.example.senati.controller;

import com.example.senati.model.entity.Entrega;
import com.example.senati.model.entity.EstadoEntrega;
import com.example.senati.service.IEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class EntregaController {

    @Autowired
    private IEntrega entregaService;

    @GetMapping("/entregas")
    public List<Entrega> getAll() {
        return entregaService.getAll();
    }

    @GetMapping("/entregas/{id}")
    public Entrega getById(@PathVariable Integer id) {
        return  entregaService.findById(id);
    }

    @PostMapping("/entregas")
    public Entrega create(@RequestBody Entrega entrega) {
        return entregaService.save(entrega);
    }

    @PutMapping("/entregas/{id}")
    public Entrega update(@PathVariable Integer id, @RequestBody Entrega entrega) {
        entrega.setId(id);
        return  entregaService.save(entrega);
    }

    @PutMapping("/entregas/{id}/estado")
    public Entrega actualizarEstadoEntrega(
            @PathVariable Integer id,
            @RequestBody EstadoEntrega estadoEntrega
    ) {
        // Aquí solo nos interesa el ID del estado que viene en el JSON
        return entregaService.actualizarEstadoEntrega(id, estadoEntrega.getId());
    }


    @GetMapping("/buscar-por-codigo/{codigoBarra}")
    public ResponseEntity<Entrega> obtenerEntregaPorCodigoBarra(@PathVariable String codigoBarra) {
        Entrega entrega = entregaService.buscarPorCodigoBarraPaquete(codigoBarra);
        return ResponseEntity.ok(entrega);
    }



    @DeleteMapping("/entregas/{id}")
    public void delete(@PathVariable Integer id) {
        Entrega entregaDelete = entregaService.findById(id);
        entregaService.delete(entregaDelete);
    }
}
