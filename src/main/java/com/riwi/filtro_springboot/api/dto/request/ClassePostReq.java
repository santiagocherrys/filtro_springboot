package com.riwi.filtro_springboot.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassePostReq {
    private String name;
    private String description;
    private Boolean active;
}
