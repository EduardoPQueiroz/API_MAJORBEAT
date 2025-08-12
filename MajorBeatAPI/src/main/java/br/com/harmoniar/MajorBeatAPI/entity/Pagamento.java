package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class Pagamento {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float valor;

    @Column
    private LocalDateTime dataPagamento;

    @Column
    private String formaPagamento;

    @Column
    private boolean status;
}
