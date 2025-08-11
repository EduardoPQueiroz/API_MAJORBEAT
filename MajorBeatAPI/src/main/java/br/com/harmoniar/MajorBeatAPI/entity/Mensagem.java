package br.com.harmoniar.MajorBeatAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table
public class Mensagem {
    private int id;

    private String texto;

    private String status;

    private LocalDateTime dataEnvio;
}
