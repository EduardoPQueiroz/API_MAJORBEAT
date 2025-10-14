package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record MensagemResponseDTO(
        Long idMensagem,
        String texto,
        LocalDateTime dataEnvio,

        @Nullable
        boolean proposta,

        @Nullable
        double valor,

        @Nullable
        Evento evento,

        Chat chat
) {
}
