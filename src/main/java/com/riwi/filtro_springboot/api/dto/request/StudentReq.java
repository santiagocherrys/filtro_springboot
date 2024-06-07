package com.riwi.filtro_springboot.api.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentReq {
    @NotBlank(message = "Se requiere el campo name")
    private String name;
    @NotBlank(message = "El campo email es requerido")
    @Email(message = "Ingrese un email valido")
    private String email;
    @NotNull(message = "El campo active es requerido")
    private boolean active;
    @NotNull(message ="Por favor ingrese user_id")
    private Long class_id;
}
