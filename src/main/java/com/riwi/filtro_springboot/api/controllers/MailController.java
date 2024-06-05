package com.riwi.filtro_springboot.api.controllers;

import com.riwi.filtro_springboot.infraestructure.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sendEmail")
public class MailController {

    @Autowired
    private EmailSenderService senderService;


    @GetMapping
    public ResponseEntity<String> sendEmail(){
        senderService.sendEmail("pruebaspringbootmail@gmail.com",
                "Prueba email",
                "This is body of email");

        return ResponseEntity.ok("Correo Enviado Correctamente");
    }
}
