package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

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

    @GetMapping
    public ResponseEntity<Page<StudentResp>> getAll(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "10") int size,
          @RequestParam(defaultValue = "Santi") String nombre,
          @RequestParam(defaultValue = "Coder alto Nivel") String descripcion
    ){
        System.out.println("Esto es nombre: " + nombre);
        System.out.println("Esto es descripción: " + descripcion);
        return ResponseEntity.ok(this.studentService.getAll(page -1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResp> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.studentService.getById(id));
    }


}
