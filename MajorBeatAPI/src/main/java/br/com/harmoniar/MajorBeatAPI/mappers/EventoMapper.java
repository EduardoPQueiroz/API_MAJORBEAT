package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ContratanteMapper.class, MusicoMapper.class })
public interface EventoMapper {

    List<EventoResponseDTO> toResponseDTOList(List<Evento> eventos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(EventoUpdateDTO dto, @MappingTarget Evento entity);

    EventoResponseDTO toDto(Evento evento);

    Evento toEntity(EventoRequestDTO dto);
}
