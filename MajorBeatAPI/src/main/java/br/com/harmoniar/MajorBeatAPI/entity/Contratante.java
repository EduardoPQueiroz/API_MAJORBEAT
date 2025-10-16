package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Contratante {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratante;

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
    private List<String> links;

    @Column
    private String nomeEmpresa;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoContratante tipoContratante;

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
