package com.buildoc.buildocDemo.services.imp;

import com.buildoc.buildocDemo.entities.Comentario;
import com.buildoc.buildocDemo.repositories.ComentarioRepository;
import com.buildoc.buildocDemo.services.ComentarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImp implements ComentarioServices {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario crearPersona(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario obtenerPersonaPorId(Long id) {
        return null;
    }

    @Override
    public Comentario actualizarPersona(Comentario comentario) {
        return null;
    }

    @Override
    public void eliminarComentario(Long id) {

    }
}
