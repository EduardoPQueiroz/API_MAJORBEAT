package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
        Long id,
        String comentario,
        LocalDateTime data,
        String avaliador,
        String recebedor
) {
}
