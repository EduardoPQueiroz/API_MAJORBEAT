package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.CandidaturaResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Candidatura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidaturaMapper {
    CandidaturaMapper INSTANCE = Mappers.getMapper(CandidaturaMapper.class);
    List<CandidaturaResponseDTO> toResponseDTOList(List<Candidatura> candidaturas);
}
