package com.example.senati.service.impl;

import com.example.senati.model.entity.Entrega; // Importa la entidad Entrega
import com.example.senati.model.entity.HistorialFotos;
import com.example.senati.model.repository.EntregaRepository; // Necesitas el repositorio de Entrega
import com.example.senati.model.repository.HistorialFotosRepository;
import com.example.senati.service.IHistorialFotos;
import com.example.senati.service.IStorageAzure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class HistorialFotosImpl implements IHistorialFotos {

    @Autowired
    private HistorialFotosRepository historialFotosRepository;

    @Autowired
    private EntregaRepository entregaRepository; // Inyecta el repositorio de Entrega

    @Autowired
    private IStorageAzure storageService;

    @Transactional(readOnly = true)
    @Override
    public List<HistorialFotos> getAll() {
        return historialFotosRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public HistorialFotos findById(Integer id) {
        return historialFotosRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public HistorialFotos save(MultipartFile file, Integer entregaId) { // Nuevo parámetro entregaId
        try {
            // Validar tipo de archivo
            String contentType = file.getContentType();
            if (contentType == null ||
                    !(contentType.equals("image/jpeg") ||
                            contentType.equals("image/png") ||
                            contentType.equals("image/gif"))) {
                throw new IllegalArgumentException("Solo se permiten archivos de imagen (JPG, PNG, GIF)");
            }

            // Generar nombre único
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            // Convertir MultipartFile a InputStream
            InputStream stream = new ByteArrayInputStream(file.getBytes());

            // Subir archivo (false porque es del contenedor fotos)
            String urlFoto = storageService.uploadFile(stream, fileName, contentType, false);

            // Buscar la entrega por su ID
            Entrega entrega = entregaRepository.findById(entregaId)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró la entrega con ID: " + entregaId));

            // Guardar en BD
            HistorialFotos historialFotos = new HistorialFotos();
            historialFotos.setUrlFoto(urlFoto);
            historialFotos.setEntrega(entrega); // Establece la relación con la entrega

            return historialFotosRepository.save(historialFotos);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar la imagen", e);
        }
    }

    @Transactional
    @Override
    public void delete(HistorialFotos historialFotos) {
        historialFotosRepository.delete(historialFotos);
    }
}