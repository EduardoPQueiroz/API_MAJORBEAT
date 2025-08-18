package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Musico{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idMusico;

    @Column
    private String nome;

    @Column
    private String apelido;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private String telefone;

    @Column
    private String endereco;

    @Column
    private byte[] fotoPerfil;

    @Column
    private String biografia;

    @Column
    private LocalDate dtCriacao;

    @Column
    private String links;

    @Column
    private String cpf;

    @Column
    private String generoPrincipal;

    @Column
    @Enumerated
    private TipoMusico tipoMusico;

    @ManyToMany
    @JoinTable(name = "MusicoEvento",
            joinColumns = @JoinColumn(name = "idMusico"),
            inverseJoinColumns = @JoinColumn(name = "idEvento")
    )
    private List<Evento> idEvento;

    @ManyToMany
    @JoinTable(name = "InstrumentoMusico",
    joinColumns = @JoinColumn(name = "idMusico"),
    inverseJoinColumns = @JoinColumn(name = "idInstrumento"))
    private List<Instrumento> idInstrumento;

    @ManyToMany
    @JoinTable(name = "DisponibilidadeMusico",
    joinColumns = @JoinColumn(name = "idMusico"),
    inverseJoinColumns = @JoinColumn(name = "idDisponibilidade"))
    private List<Disponibilidade> idDisponibilidade;

    @ManyToMany
    @JoinTable(name = "GeneroMusicalMusico",
    joinColumns = @JoinColumn(name = "idMusico"),
    inverseJoinColumns = @JoinColumn(name = "idGeneroMusical"))
    private List<GeneroMusical> idGeneroMusical;

}
