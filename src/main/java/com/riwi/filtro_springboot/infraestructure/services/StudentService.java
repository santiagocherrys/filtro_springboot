package com.riwi.filtro_springboot.infraestructure.services;


import com.riwi.filtro_springboot.api.dto.request.ClasseReq;
import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseResp;
import com.riwi.filtro_springboot.api.dto.response.ClasseToStudentResp;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;
import com.riwi.filtro_springboot.domain.entities.Classe;
import com.riwi.filtro_springboot.domain.entities.Student;
import com.riwi.filtro_springboot.domain.repositories.StudentRepository;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IClasseService;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService {


    @Autowired
    private final StudentRepository studentRepository;
    @Override
    public void delete(Long id) {

    }

    @Override
    public StudentResp create(StudentReq request) {
        Student student = this.requestToEntity(request);

        return this.entityToResponse(this.studentRepository.save(student));
    }

    @Override
    public StudentResp update(Long id, StudentReq request) {
        return null;
    }

    @Override
    public Page<StudentResp> getAll(int page, int size) {
        return null;
    }

    @Override
    public StudentResp getById(Long id) {
        return null;
    }

    private Student requestToEntity(StudentReq student){

        return Student.builder()
                .name(student.getName())
                .email(student.getEmail())
                .active(student.isActive())
                .build();
    }

    private StudentResp entityToResponse(Student entity){

        StudentResp studentResp = new StudentResp();

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
}
