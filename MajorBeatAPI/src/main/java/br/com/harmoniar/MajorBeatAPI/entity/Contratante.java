package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
public class Contratante {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String nomeEmpresa;

    @Column
    private String cpf;

    @Column
    private String cnpj;

    @Column
    @Enumerated
    private TipoContratante tipoContratante;
}
