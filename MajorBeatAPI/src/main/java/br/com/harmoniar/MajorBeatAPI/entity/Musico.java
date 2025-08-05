package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class Musico{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

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

}
