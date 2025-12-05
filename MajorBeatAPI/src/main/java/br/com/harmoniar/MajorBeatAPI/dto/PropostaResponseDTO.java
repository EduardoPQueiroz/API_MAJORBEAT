package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.StatusProposta;

import java.util.Date;

public record PropostaResponseDTO(

        Long idProposta,
        Date dataEnvio,
        double valor,
        StatusProposta statusProposta,
        Contratante contratante,
        Musico musico,
        Long idRemetente,
        Long idRecebedor,

        EventoResponseDTO evento
) {
}
