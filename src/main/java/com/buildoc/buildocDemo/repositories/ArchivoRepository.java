package com.buildoc.buildocDemo.repositories;

import com.buildoc.buildocDemo.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}
