package com.riwi.filtro_springboot.infraestructure.services;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseToStudentResp;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;
import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Student;
import com.riwi.filtro_springboot.domain.repositories.ClasseRepository;
import com.riwi.filtro_springboot.domain.repositories.StudentRepository;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IStudentService;
import com.riwi.filtro_springboot.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {


    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final ClasseRepository classeRepository;
    @Override
    public void delete(Long id) {

    }

    @Override
    public StudentResp create(StudentReq request) {

        System.out.println("esto es request" + request);
        System.out.println("this is class_id"+ request.getClass_id());
        //Se busca la clase
        Classe classe = this.classeRepository.findById(request.getClass_id()).orElseThrow(() -> new IdNotFoundException("Class"));
        Student student = this.requestToEntity(request);
        //Se setea la clase
        student.setClasse(classe);
        //System.out.println("Esto es student" + classe);
        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResp update(Long id, StudentReq request) {
        //Se busca el estudiante
        Student studenthelp = this.find(id);

        //Se pasa request a student
        Student student = this.requestToEntity(request);
        student.setId(studenthelp.getId());
        //Se actualiza la hora
        LocalDateTime lt = LocalDateTime.now();
        student.setCreated_at(lt);
        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public Page<StudentResp> getAll(int page, int size) {
        return null;
    }

    @Override
    public StudentResp getById(Long id) {

        return this.entityToResponse(this.find(id));
    }


    private Student requestToEntity(StudentReq student){
        //Encontrar Clase


        return Student.builder()
                .name(student.getName())
                .email(student.getEmail())
                .active(student.isActive())
                .classe(this.classeRepository.findById(student.getClass_id()).orElseThrow(() -> new IdNotFoundException("Class")))
                .build();
    }

    private StudentResp entityToResponse(Student entity){

        StudentResp studentResp = new StudentResp();

        System.out.println("Esto es la salida de entity"+ entity);
        //studentResp.setCreated_at(entity.getCreated_at());
        BeanUtils.copyProperties(entity,studentResp);
        studentResp.setClasse(this.entityToClassRespo(entity.getClasse()));


        return studentResp;
    }

    private ClasseToStudentResp entityToClassRespo(Classe classe){
        return ClasseToStudentResp.builder()
                .id(classe.getId())
                .name(classe.getName())
                .description(classe.getDescription())
                .created_at(classe.getCreated_at())
                .active(classe.isActive())
                .build();
    }

    private Student find(Long id){
        return this.studentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Student"));
    }

    @Override
    public StudentResp disableById(Long id) {
        Student student = this.find(id);
        //Se pone el estado a false
        student.setActive(false);
        //Se guarda en la base de datos
        this.studentRepository.save(student);
        return this.entityToResponse(student);
    }
}
