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
    public void crearPersona(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public Comentario obtenerPersonaPorId(Long id) {
        return comentarioRepository.getById(id);
    }

    @Override
    public void actualizarPersona(Comentario comentario) {
        comentarioRepository.save(comentario);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }


}
}
