package com.riwi.filtro_springboot.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassePostReq {
    @NotBlank(message = "El campo name es requerido")
    private String name;
    @NotBlank(message = "El campo description es requerido")
    private String description;
    @NotNull(message = "El campo active es requerido")
    private boolean active;
}
