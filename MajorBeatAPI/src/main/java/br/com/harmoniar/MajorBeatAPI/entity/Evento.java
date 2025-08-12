package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Table
@Entity
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private TipoMusico tipoMusico;

    @Column
    private LocalDate data;

    @Column
    private String endereco;

    @Enumerated
    private StatusEvento status;

    @Column
    private byte[] imagemLocalEvento;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horaFim;

    @Column
    private String descricao;

    @Column
    private String titulo;


}
