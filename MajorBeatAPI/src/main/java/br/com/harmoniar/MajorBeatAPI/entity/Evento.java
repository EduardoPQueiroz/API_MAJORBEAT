package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idEvento;

    @Column
    private String nome;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoMusico tipoMusico;

    @Column
    private LocalDate data;

    @Column
    private String endereco;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEvento status;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalTime horaFim;

    @Column
    private String descricao;

    @Column
    private String titulo;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<NomeInstrumento> instrumentos = new ArrayList<>();

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<NomeGenero> generos = new ArrayList<>();

    @JoinColumn(nullable = true)
    @ManyToOne
    private Musico musico;

    @JoinColumn
    @ManyToOne
    private Contratante contratante;

    @Column
    @ElementCollection
    private List<String> mediaUrl = new ArrayList<>();

}
