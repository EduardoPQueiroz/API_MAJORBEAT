package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record CandidaturaResponseDTO(
        int idCandidatura,
        LocalDateTime dataEnvio,
        String status
) {
}
