package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.DisponibillidadeResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Disponibilidade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisponibilidadeMapper {
    DisponibilidadeMapper INSTANCE = Mappers.getMapper(DisponibilidadeMapper.class);
    List<DisponibillidadeResponseDTO> toResponseDTOList(List<Disponibilidade> disponibilidades);
}
