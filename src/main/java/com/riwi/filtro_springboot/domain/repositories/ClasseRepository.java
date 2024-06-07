package com.riwi.filtro_springboot.domain.repositories;

import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    List<Classe> findByNameAndActive(String name, boolean active);
}
