package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Archivo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArchivoServices {
    public List<Archivo> listarArchivos();

    Archivo crearArchivo(Archivo archivo);

    Archivo obtenerArchivoPorId(Long id);

    Archivo actualizarArchivo(Archivo archivo);

    void eliminarArchivo(Long id);
}
