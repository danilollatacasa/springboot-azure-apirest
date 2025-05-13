package com.example.senati.controller;

import com.example.senati.model.entity.HistorialFotos;
import com.example.senati.service.IEntrega; // Necesitas el servicio de Entrega
import com.example.senati.service.IHistorialFotos;
import com.example.senati.service.IStorageAzure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HistorialFotosController {

    @Autowired
    private IStorageAzure storageAzureService;

    @Autowired
    private IHistorialFotos historialFotosService;

    @Autowired
    private IEntrega entregaService; // Inyecta el servicio de Entrega

    @GetMapping("/historialfotos")
    public List<HistorialFotos> getAll() {
        return historialFotosService.getAll();
    }

    @GetMapping("/historialfotos/{id}")
    public HistorialFotos findById(@PathVariable Integer id){
        return historialFotosService.findById(id);
    }

    @PostMapping(value = "/historialfotos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<HistorialFotos> uploadFotos(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam(value = "file2", required = false) MultipartFile file2,
            @RequestParam("entregaId") Integer entregaId
    ) {
        List<MultipartFile> files = new ArrayList<>();
        if (file1 != null) files.add(file1);
        if (file2 != null) files.add(file2);

        List<HistorialFotos> fotosGuardadas = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                HistorialFotos foto = historialFotosService.save(file, entregaId);
                fotosGuardadas.add(foto);
            }
        }
        return fotosGuardadas;
    }




    @DeleteMapping("/historialfotos/{id}")
    public void delete(@PathVariable Integer id){
        HistorialFotos historialFotosDelete = historialFotosService.findById(id);
        historialFotosService.delete(historialFotosDelete);
    }
}