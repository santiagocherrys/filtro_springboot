package com.riwi.filtro_springboot;

import com.riwi.filtro_springboot.infraestructure.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FiltroSpringbootApplication {
	//@Autowired
	//private EmailSenderService senderService;
	public static void main(String[] args) {

		SpringApplication.run(FiltroSpringbootApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	//public void sendMail(){
		//senderService.sendEmail("pruebaspringbootmail@gmail.com",
		//	"Prueba email",
		//		"This is body of email");
	//}

}
