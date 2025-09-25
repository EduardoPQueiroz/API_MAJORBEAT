package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record EventoUpdateDTO(
        String nome,
        String tipoMusico,
        LocalDateTime data,
        String endereco,
        StatusEvento statusEvento,
        byte[] imagemLocalEvento,
        LocalTime horaInicio,
        List<NomeInstrumento> instrumentos,
        List<NomeGenero> generos,
        LocalTime horaFim,
        String descricao,
        String titulo
) {
}
