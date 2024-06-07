package com.riwi.filtro_springboot.infraestructure.services;

import com.riwi.filtro_springboot.api.dto.request.ClasseReq;
import com.riwi.filtro_springboot.api.dto.request.StudentToClassResp;
import com.riwi.filtro_springboot.api.dto.response.ClasseResp;
import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Student;
import com.riwi.filtro_springboot.domain.repositories.ClasseRepository;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IClasseService;
import com.riwi.filtro_springboot.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClasseService implements IClasseService {
    @Autowired
    private final ClasseRepository classeRepository;

    @Override
    public void delete(Long id) {

    }

    @Override
    public ClasseResp create(ClasseReq request) {
        Classe classe = this.requestToEntity(request);
        classe.setStudents(new ArrayList<>());
        //Se crea los campos vacios
        ClasseResp classResp = this.entityToResponse(this.classeRepository.save(classe));


        return classResp;
    }

    @Override
    public ClasseResp update(Long id, ClasseReq request) {
        return null;
    }

    @Override
    public Page<ClasseResp> getAll(int page, int size) {
        if(page < 0){
            page = 0;
        }

        PageRequest pagination = null;
        pagination = PageRequest.of(page, size);

        return this.classeRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public ClasseResp getById(Long id) {
        return  this.entityToResponse(this.find(id));
    }
    private Classe requestToEntity(ClasseReq classe){
        //String hora = "2024-06-07 20:00:00";
        //classe.setCreated_at(LocalDateTime.parse(hora));


        return Classe.builder()
                .name(classe.getName())
                .description(classe.getDescription())
                //.created_at(classe.getCreated_at())
                .active(classe.isActive())
                .build();
    }

    private ClasseResp entityToResponse(Classe entity){
        //Se crea instancia de la respuesta
        ClasseResp classeResp = new ClasseResp();
        BeanUtils.copyProperties(entity,classeResp);
        classeResp.setId(entity.getId());

        //Se lee la lista de estudiantes
        List<StudentToClassResp> students = entity.getStudents()
                .stream()
                .map(this::entityToStudentRespo).collect(Collectors.toList());

        classeResp.setStudents(students);
        return classeResp;
    }

    private StudentToClassResp entityToStudentRespo(Student student){
        return StudentToClassResp.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .created_at(student.getCreated_at())
                .active(student.isActive())
                .build();
    }

    private Classe find(Long id){
        return this.classeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Class"));
    }
}
