package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;

public record InstrumentoResponseDTO(
        Long idInstrumento,
        NomeInstrumento nomeInstrumento
) {
}
