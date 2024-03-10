package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Comentario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ComentarioServices {
    public List<Comentario> listarComentarios();

    public void crearComentario(Comentario comentario);

    public Comentario obtenerComentarioPorId(Long id);

    public void actualizarComentario(Comentario comentario);

    public void eliminarComentario(Comentario comentario);
}
