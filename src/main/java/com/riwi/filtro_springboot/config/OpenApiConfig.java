package com.riwi.filtro_springboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

//Para configurar beans dentro de spring
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Api para Endpoints Filtro Springboot",
                version = "1.0",
                description = "Esta api se utiliza para poder visualizar todos los endpoints del filtro de spring"
        )
)
public class OpenApiConfig {

}
