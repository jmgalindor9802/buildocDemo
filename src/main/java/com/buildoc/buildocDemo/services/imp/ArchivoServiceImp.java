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
    public void crearArchivo(Archivo archivo) {
         archivoRepository.save(archivo);
    }
    @Override
    public Archivo obtenerArchivoPorId(Long id) {
        return archivoRepository.getById(id);
    }
    @Override
    public void actualizarArchivo(Archivo archivo) {
         archivoRepository.save(archivo);
    }
    @Override
    public void eliminarArchivo(Archivo archivo) {
     archivoRepository.delete(archivo);
    }
}
