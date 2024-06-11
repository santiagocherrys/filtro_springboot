package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.api.dto.response.LessonResp;
import com.riwi.filtro_springboot.infraestructure.abstract_services.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<LessonResp> disable(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(this.lessonService.disableById(id));
    }
}

