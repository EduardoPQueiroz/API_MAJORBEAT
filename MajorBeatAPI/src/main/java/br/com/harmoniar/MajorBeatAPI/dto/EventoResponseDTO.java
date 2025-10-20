package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoEvento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record EventoResponseDTO(
        Long idEvento,
        String nome,
        String tipoMusico,
        LocalDate data,
        String endereco,
        TipoEvento tipoEvento,
        List<byte[]> imagemLocalEvento,
        LocalTime horaInicio,
        List<NomeInstrumento> instrumentos,
        List<NomeGenero> generos,
        LocalTime horaFim,
        String descricao
) {
}
