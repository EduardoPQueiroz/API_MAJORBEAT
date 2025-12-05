package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record EventoResponseDTO(
        Long idEvento,
        String nome,
        TipoMusico tipoMusico,
        LocalDate data,
        String endereco,
        TipoEvento tipoEvento,
        List<String> mediaUrl,
        LocalTime horaInicio,
        List<NomeInstrumento> instrumentos,
        List<NomeGenero> generos,
        ContratanteResponseDTO contratante,
        LocalTime horaFim,
        String descricao
) {
}
