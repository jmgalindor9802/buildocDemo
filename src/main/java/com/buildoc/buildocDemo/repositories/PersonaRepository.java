package com.buildoc.buildocDemo.repositories;
import com.buildoc.buildocDemo.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
