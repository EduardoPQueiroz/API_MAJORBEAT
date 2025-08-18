package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record DisponibillidadeResponseDTO(
        Long idDisponibilidade,
        LocalDateTime inicioDispo,
        LocalDateTime fimDispo
) {
}
