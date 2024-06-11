package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.response.LessonResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private final ILessonService lessonService;

    @GetMapping(path = "/{id}/multimedia")
    public ResponseEntity<LessonResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.lessonService.getById(id));
    }
}

