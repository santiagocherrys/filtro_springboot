package com.riwi.filtro_springboot.api.dto.response;

import com.riwi.filtro_springboot.domain.entities.Classe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResp {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private  boolean active;
    private ClasseBasicResp classe;
    private List<MultimediaResp> multimedias;

}
