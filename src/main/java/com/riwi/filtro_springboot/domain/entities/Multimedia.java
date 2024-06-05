package com.riwi.filtro_springboot.domain.entities;

import com.riwi.filtro_springboot.util.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "multimedia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)//Esto hace que imprima el string y no un numero
    @Column(nullable = false)
    private Type type;
    @Column(columnDefinition = "Text", nullable = false)
    private String url;
    @CreationTimestamp
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

}
