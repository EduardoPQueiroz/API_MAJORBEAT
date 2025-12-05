package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.StatusProposta;

public record PropostaUpdateDTO(
        StatusProposta statusProposta
) {
}
