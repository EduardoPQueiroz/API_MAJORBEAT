package br.com.harmoniar.MajorBeatAPI.entity;

import br.com.harmoniar.MajorBeatAPI.enums.StatusProposta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Table
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int idProposta;

    @Column
    public double valor;

    @Column
    public LocalDate DataEnvio;

    @Column
    @Enumerated(EnumType.STRING)
    public StatusProposta statusProposta;

    @JoinColumn
    @ManyToOne
    public Contratante contratante;

    @JoinColumn
    @ManyToOne
    public Musico musico;

    @Column
    public Long idRemetente;

    @Column
    public Long idRecebedor;

    @JoinColumn
    @ManyToOne
    public Evento evento;
}
