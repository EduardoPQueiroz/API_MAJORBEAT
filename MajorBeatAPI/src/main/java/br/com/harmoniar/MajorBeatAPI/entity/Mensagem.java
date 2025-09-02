package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table
public class Mensagem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idMensagem;

    @Column
    private String texto;

    @Column
    private String status;

    @Column
    private LocalDateTime dataEnvio;

    @JoinColumn
    @ManyToOne
    private Chat chat;

    @JoinColumn
    @ManyToOne
    private Musico idMusico;

    @JoinColumn
    @ManyToOne
    private Contratante idContratante;
}
