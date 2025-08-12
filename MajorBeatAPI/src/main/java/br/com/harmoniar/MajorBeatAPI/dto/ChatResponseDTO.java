package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record ChatResponseDTO(
        Long id,
        LocalDateTime dataInicio
) {
}
