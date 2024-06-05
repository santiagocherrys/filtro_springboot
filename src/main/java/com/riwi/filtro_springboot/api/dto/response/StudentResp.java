package com.riwi.filtro_springboot.api.dto.response;

import com.riwi.filtro_springboot.domain.entities.Classe;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResp {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private boolean active;
    private ClasseToStudentResp classe;
}
