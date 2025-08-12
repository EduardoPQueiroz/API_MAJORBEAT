package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record CandidaturaResponseDTO(
        int id,
        LocalDateTime dataEnvio,
        String status
) {
}
