package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = { ContratanteMapper.class, MusicoMapper.class, AvaliacaoMapper.class, ChatMapper.class })
public interface EventoMapper {
    List<EventoResponseDTO> toResponseDTOList(List<Evento> eventos);

    void updateFromDto(EventoUpdateDTO dto, @MappingTarget Evento entity);
    @Mapping(target = "contratante", source = "contratante")
    @Mapping(target = "musico", source = "musico")

    EventoResponseDTO toDto(Evento evento);

    Evento toEntity(EventoRequestDTO dto);
}
