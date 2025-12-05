package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Evento;

public record PropostaRequestDTO(
        double valor,

        Long idRecebedor,
        EventoRequestDTO evento
) {
}
