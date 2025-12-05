package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.entity.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropostaMapper {
    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    List<PropostaResponseDTO> toResponseDTOList(List<Proposta> propostas);

    Proposta toEntity(PropostaRequestDTO dto);
    void updateFromDto(PropostaUpdateDTO dto, @MappingTarget Proposta entity);
    PropostaResponseDTO toDto(Proposta proposta);

}
