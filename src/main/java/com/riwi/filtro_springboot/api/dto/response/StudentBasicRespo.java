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
public class StudentBasicRespo {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private boolean active;
}
