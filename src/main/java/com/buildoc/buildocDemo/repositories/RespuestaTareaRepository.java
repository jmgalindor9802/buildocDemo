package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.RespuestaTarea;
import com.buildoc.buildocDemo.entities.Tarea;
import com.buildoc.buildocDemo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RespuestaTareaRepository  extends JpaRepository<RespuestaTarea, Long> {
    List<RespuestaTarea> findByTarea(Tarea tarea);

    List<RespuestaTarea> findByUsuario(Usuario usuario);


}
