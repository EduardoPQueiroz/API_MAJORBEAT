package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.*;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idChat;

    @Column
    private LocalDateTime dataInicio;

    @JoinColumn
    @ManyToOne
    private Musico musico;

    @JoinColumn
    @ManyToOne
    private Contratante contratante;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Mensagem> mensagens = new ArrayList<>();


}
