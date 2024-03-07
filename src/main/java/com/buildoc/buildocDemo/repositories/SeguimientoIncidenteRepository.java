package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Archivo;
import com.buildoc.buildocDemo.entities.SeguimientoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoIncidenteRepository extends JpaRepository<SeguimientoIncidente, Long> {
    public List<SeguimientoIncidente> listarSeguimientoIncidentes();

    public void crearSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    public Archivo obtenerSeguimientoIncidentePorId(Long id);

    public void actualizarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);

    public void eliminarSeguimientoIncidente(SeguimientoIncidente seguimientoIncidente);
}
