package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Avaliacao {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;

    @Column
    private int nota;

    @Column
    private String comentario;

    @Column
    private LocalDateTime data;

    @Column
    private String avaliador;

    @Column
    private String recebedor;

}
