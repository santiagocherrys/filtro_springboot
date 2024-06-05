package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.request.ClassePostReq;
import com.riwi.filtro_springboot.api.dto.request.ClasseReq;
import com.riwi.filtro_springboot.api.dto.response.ClasseResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.IClasseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/class")
@AllArgsConstructor
public class ClasseController {
    @Autowired
    private final IClasseService classeService;

    @PostMapping
    public ResponseEntity<ClasseResp> insert(
            @Validated
            @RequestBody ClassePostReq request){
        ClasseReq classeReq = new ClasseReq();

        //Se mapea los datos
        classeReq.setName(request.getName());
        classeReq.setDescription(request.getDescription());
        classeReq.setActive(request.isActive());
        return ResponseEntity.ok(this.classeService.create(classeReq));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClasseResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.classeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClasseResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.classeService.getAll(page -1 , size));
    }
}
