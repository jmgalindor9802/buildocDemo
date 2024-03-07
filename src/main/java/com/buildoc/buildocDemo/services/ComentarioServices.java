package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.Comentario;

import java.util.List;

public interface ComentarioServices {
    public List<Comentario> listarComentarios();

    Comentario crearPersona(Comentario comentario);

    Comentario obtenerPersonaPorId(Long id);

    Comentario actualizarPersona(Comentario comentario);

    void eliminarComentario(Long id);
}
