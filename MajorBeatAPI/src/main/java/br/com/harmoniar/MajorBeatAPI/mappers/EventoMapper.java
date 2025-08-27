package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);
    List<EventoResponseDTO> toResponseDTOList(List<Evento> eventos);

    EventoResponseDTO OptionaltoDto(Optional<Evento> evento);

    EventoResponseDTO toDto(Evento evento);

    Evento toEntity(EventoResponseDTO dto);
}
