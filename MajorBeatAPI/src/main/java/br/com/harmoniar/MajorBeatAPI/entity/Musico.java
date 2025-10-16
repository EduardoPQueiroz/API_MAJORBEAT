package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ElementCollection
    private List<String> links = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private TipoMusico tipoMusico;

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<NomeInstrumento> nomeInstrumentos = new ArrayList<>();

    @Column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<NomeGenero> nomeGeneros = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @ElementCollection
    private List<String> mediaUrl = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();

}
