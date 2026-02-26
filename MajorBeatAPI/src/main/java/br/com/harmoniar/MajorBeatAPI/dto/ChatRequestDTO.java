package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;

public record ChatRequestDTO(
        Musico musico,
        Contratante contratante
) {
}
