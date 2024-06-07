package com.riwi.filtro_springboot.domain.repositories;

import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //Busca por nombre estudiante
    List<Student> findByNameAndActive(String name, boolean active);

    //Buscar por descripcion de clase
    @Query(nativeQuery = true, value = "SELECT student.id, student.active, student.created_at, student.email, student.name, student.class_id FROM student INNER JOIN class ON student.class_id = class.id WHERE class.name = 'SpringBoot'")
    List<Student> findByNombreClase();

    @Query(nativeQuery = true, value = "SELECT student.id, student.active, student.created_at, student.email, student.name, student.class_id FROM student INNER JOIN class ON student.class_id = class.id WHERE class.description = :description")
    List<Student> findByDescription(@Param("description") String description);

    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE name= :name")
    List<Student> findByNombre(@Param("name") String name);
}
