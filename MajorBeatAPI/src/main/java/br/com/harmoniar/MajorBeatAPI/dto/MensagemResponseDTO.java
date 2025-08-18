package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record MensagemResponseDTO(
        Long idMensagem,
        String texto,
        String status,
        LocalDateTime dataEnvio,
        LocalTime hora
) {
}
