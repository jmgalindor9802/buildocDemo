package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Archivo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArchivoServices {
    public List<Archivo> listarArchivos();

    public void crearArchivo(Archivo archivo);

    public Archivo obtenerArchivoPorId(Long id);

    public void actualizarArchivo(Archivo archivo);

    public void eliminarArchivo(Archivo archivo);
}
