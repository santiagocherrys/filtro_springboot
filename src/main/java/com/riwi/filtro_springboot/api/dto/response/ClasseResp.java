package com.riwi.filtro_springboot.api.dto.response;

import com.riwi.filtro_springboot.api.dto.request.StudentToClassResp;
import com.riwi.filtro_springboot.domain.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClasseResp {
    private Long id;
    private String name;
    private LocalDateTime created_at;
    private String description;
    private boolean active;
    private List<StudentToClassResp> students;
    //private List<LessonToClassResp>  lessons;
}
