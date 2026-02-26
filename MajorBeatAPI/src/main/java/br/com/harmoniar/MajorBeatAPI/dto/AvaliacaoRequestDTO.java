package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;

import java.time.LocalDateTime;

public record AvaliacaoRequestDTO(

        Long nota,
        String comentario,

        Long idRecebedor
) {
}
