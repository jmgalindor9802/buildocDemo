package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraeaRepository extends JpaRepository<Tarea, Long> {
}
