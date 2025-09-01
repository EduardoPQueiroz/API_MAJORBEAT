package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import jdk.jfr.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idChat;

    @Column
    private LocalDateTime dataInicio;

    @JoinColumn
    @ManyToOne
    private Evento idEvento;

    @JoinColumn
    @ManyToOne
    private Musico idMusico;

    @JoinColumn
    @ManyToOne
    private Contratante idContratante;


}
