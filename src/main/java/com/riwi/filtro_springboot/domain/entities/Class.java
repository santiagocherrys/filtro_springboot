package com.riwi.filtro_springboot.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private boolean active;

    //Relaciones
    @OneToMany(mappedBy = "classe",
                cascade =CascadeType.ALL,
                orphanRemoval = false,
                fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students;

    @OneToMany(mappedBy = "classe",
                cascade = CascadeType.ALL,
                orphanRemoval = false,
                fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;

}
