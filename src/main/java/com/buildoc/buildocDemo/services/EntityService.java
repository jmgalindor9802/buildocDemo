package com.buildoc.buildocDemo.services;

import com.buildoc.buildocDemo.entities.enums.EstadoDato;

import java.util.List;

public interface EntityService<T> {
    void cambiarEstadoDato(Long id, EstadoDato estado);

    List<T> listarEntidadesActivas();
}
