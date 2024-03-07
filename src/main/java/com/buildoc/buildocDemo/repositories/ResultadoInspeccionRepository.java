package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.ResultadoInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoInspeccionRepository extends JpaRepository<ResultadoInspeccion, Long> {
}
