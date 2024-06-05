package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.request.StudentReq;
import com.riwi.filtro_springboot.api.dto.response.StudentResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


}
