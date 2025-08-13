package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratanteMapper {
    ContratanteMapper INSTANCE = Mappers.getMapper(ContratanteMapper.class);
    List<ContratanteResponseDTO> toResponseDTOList(List<Contratante> contratantes);
}
