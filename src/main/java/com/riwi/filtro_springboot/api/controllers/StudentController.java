package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.StudentBasicRespo;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController{
    @Autowired
    private final IStudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResp> insert(
            @Validated
            @RequestBody StudentReq request){

        return ResponseEntity.ok(this.studentService.create(request));
    }

    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<StudentResp> disable(@PathVariable Long id){

        return ResponseEntity.ok(this.studentService.disableById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentResp> update(@PathVariable Long id,
            @Validated
            @RequestBody StudentReq request){
        return ResponseEntity.ok(this.studentService.update(id,request));
    }

    //@GetMapping
    //public ResponseEntity<Page<StudentResp>> getAll(
      //    @RequestParam(defaultValue = "1") int page,
      //    @RequestParam(defaultValue = "10") int size,
      //    @RequestParam(defaultValue = "Santi") String nombre,
      //    @RequestParam(defaultValue = "Coder alto Nivel") String descripcion
    //){
    //    System.out.println("Esto es nombre: " + nombre);
    //    System.out.println("Esto es descripción: " + descripcion);
    //    return ResponseEntity.ok(this.studentService.getAll(page -1, size));
    //}

    @GetMapping
    public ResponseEntity<List<StudentResp>> getAll(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String description
    ){
        if(description.equals("") && name.equals("")){
            return ResponseEntity.ok(this.studentService.findAll());
        }else if(description.equals("")){
            List<StudentBasicRespo> students =  this.studentService.findStudentsByNameClass(name);
            return ResponseEntity.ok(students.stream().map(this::entityToStudentResp).collect(Collectors.toList()));
        }else if(name.equals("")){
            List<StudentBasicRespo> students = this.studentService.findStudentsByDescriptionClass(description);
            return ResponseEntity.ok(students.stream().map(this::entityToStudentResp).collect(Collectors.toList()));
        }else{
            return ResponseEntity.ok(this.studentService.findAll());
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResp> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.studentService.getById(id));
    }

    private StudentResp entityToStudentResp(StudentBasicRespo studentbasic){
        StudentResp studentResp = new StudentResp();
        studentResp.setClasse(null);
        studentResp.setId(studentbasic.getId());
        studentResp.setName(studentbasic.getName());
        studentResp.setEmail(studentbasic.getEmail());
        studentResp.setActive(studentbasic.isActive());
        studentResp.setCreated_at(studentbasic.getCreated_at());
        return studentResp;
    }

}
