package com.riwi.filtro_springboot.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClasseBasicResp {
    private Long id;
    private String name;
    private LocalDateTime created_at;
    private String description;
    private boolean active;
}
