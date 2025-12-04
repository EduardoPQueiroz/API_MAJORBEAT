package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record EventoRequestDTO(
        String nome,
        TipoMusico tipoMusico,
        LocalDate data,
        String endereco,
        List<String> mediaUrl,
        LocalTime horaInicio,
        TipoEvento tipoEvento,
        List<NomeInstrumento> instrumentos,
        List<NomeGenero> generos,
        LocalTime horaFim,
        String descricao
) {
}
