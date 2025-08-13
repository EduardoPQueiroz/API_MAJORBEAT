package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);
    List<EventoResponseDTO> toResponseDTOList(List<Evento> eventos);
}
