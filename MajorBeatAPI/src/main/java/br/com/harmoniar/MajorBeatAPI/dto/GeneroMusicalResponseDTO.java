package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;

public record GeneroMusicalResponseDTO(
        Long id,
        NomeGenero nomeGenero
) {
}
