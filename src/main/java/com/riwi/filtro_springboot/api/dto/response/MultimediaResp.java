package com.riwi.filtro_springboot.api.dto.response;

import com.riwi.filtro_springboot.util.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaResp {
    private Long id;
    private Type type;
    private String url;
    private LocalDateTime created_at;
    private boolean active;
}
