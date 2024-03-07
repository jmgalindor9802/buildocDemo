package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Comentario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ComentarioServices {
    public List<Comentario> listarComentarios();

    public void crearPersona(Comentario comentario);

    public Comentario obtenerPersonaPorId(Long id);

    public void actualizarPersona(Comentario comentario);

    public void eliminarComentario(Comentario comentario);
}
