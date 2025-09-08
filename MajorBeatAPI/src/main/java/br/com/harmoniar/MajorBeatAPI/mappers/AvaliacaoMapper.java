package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);
    List<AvaliacaoResponseDTO> toResponseDTOList(List<Avaliacao> avaliacoes);

    Avaliacao toEntity(AvaliacaoRequestDTO dto);
    AvaliacaoResponseDTO toDto(Avaliacao avaliacao);
}
