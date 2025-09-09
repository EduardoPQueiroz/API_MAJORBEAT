package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record MensagemRequestDTO(
        String texto,

        @Nullable
        boolean proposta,

        @Nullable
        double valor,

        @Nullable
        Evento evento
) {
}
