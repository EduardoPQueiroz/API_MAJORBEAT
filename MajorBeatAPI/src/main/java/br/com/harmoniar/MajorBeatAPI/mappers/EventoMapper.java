package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);
    List<EventoResponseDTO> toResponseDTOList(List<Evento> eventos);

    EventoResponseDTO OptionaltoDto(Optional<Evento> evento);

    void updateFromDto(EventoUpdateDTO dto, @MappingTarget Evento entity);
    EventoResponseDTO toDto(Evento evento);

    Evento toEntity(EventoRequestDTO dto);
}
