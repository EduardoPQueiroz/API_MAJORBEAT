package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventoResponseDTO(
        Long idEvento,
        String nome,
        String tipoMusico,
        LocalDateTime data,
        float cacheEvento,
        String endereco,
        StatusEvento statusEvento,
        byte[] imagemLocalEvento,
        LocalTime horaInicio,
        LocalTime horaFim,
        String descricao,
        String titulo
) {
}
