package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record DisponibillidadeResponseDTO(
        Long id,
        LocalDateTime inicioDispo,
        LocalDateTime fimDispo
) {
}
