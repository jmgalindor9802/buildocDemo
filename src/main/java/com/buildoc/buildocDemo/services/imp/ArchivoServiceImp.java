package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.repositories.ArchivoRepository;
import com.buildoc.buildocDemo.services.ArchivoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivoServiceImp implements ArchivoServices {
    @Autowired
    private ArchivoRepository archivoRepository;
    @Override
    public List<Archivo> listarArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    public Archivo crearArchivo(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    @Override
    public Archivo obtenerArchivoPorId(Long id) {
        return null;
    }

    @Override
    public Archivo actualizarArchivo(Archivo archivo) {
        return null;
    }

    @Override
    public void eliminarArchivo(Long id) {

    }
}
