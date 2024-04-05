package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Inspeccion;
import com.buildoc.buildocDemo.entities.TipoInspeccion;
import com.buildoc.buildocDemo.entities.enums.EstadoDato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspeccionRepository extends JpaRepository<Inspeccion, Long> {
    List<Inspeccion> findByEstadoDato(EstadoDato estadoDato);
}
