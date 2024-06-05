package com.riwi.filtro_springboot.domain.entities;

import com.riwi.filtro_springboot.util.enums.Type;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)//Esto hace que imprima el string y no un numero
    @Column(nullable = false)
    private Type type;
    @Column(columnDefinition = "Text", nullable = false)
    private String url;
    @Column(nullable = false)
    private Long lesson_id;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

}
