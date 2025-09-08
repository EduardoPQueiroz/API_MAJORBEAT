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
    private Long nota;

    @Column
    private String comentario;

    @Column
    private LocalDateTime data;

    @Column
    private Long idAvaliador;

    @Column
    private Long idRecebedor;

    @JoinColumn
    @ManyToOne
    private Musico idMusico;

    @JoinColumn
    @ManyToOne
    private Contratante idContratante;

}
