package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
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

    @Column
    @ElementCollection
    @Enumerated
    private List<NomeInstrumento> nomeInstrumento = new ArrayList<>();

    @Column
    @ElementCollection
    @Enumerated
    private List<NomeGenero> nomeGenero = new ArrayList<>();

    @JoinColumn
    @ManyToOne
    private Musico idMusico;

    @ManyToOne
    private Contratante idContratante;

    @ManyToMany
    @JoinTable(name = "AvaliacaoEvento",
    joinColumns = @JoinColumn(name = "idEvento"),
    inverseJoinColumns = @JoinColumn(name = "idAvaliacao"))
    private List<Avaliacao> avaliacoes;
}
