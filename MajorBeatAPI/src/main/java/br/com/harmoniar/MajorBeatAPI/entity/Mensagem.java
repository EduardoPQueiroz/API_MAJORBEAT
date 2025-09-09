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
    private LocalDateTime dataEnvio;

    @Column
    private boolean proposta;

    @Column
    private double valor;

    @JoinColumn
    @ManyToOne
    private Evento evento;

    @JoinColumn
    @ManyToOne
    private Chat chat;

    @Column
    private Long idRemetente;
}
