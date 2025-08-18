package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idCandidatura;

    @Column
    private LocalDateTime dataEnvio;

    @Column
    private String status;
    
}
