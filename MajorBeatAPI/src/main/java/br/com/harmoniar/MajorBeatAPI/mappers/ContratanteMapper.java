package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.ContratanteResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.ContratanteUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MusicoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ContratanteMapper {
    ContratanteMapper INSTANCE = Mappers.getMapper(ContratanteMapper.class);
    List<ContratanteResponseDTO> toResponseDTOList(List<Contratante> contratantes);

    Contratante toEntity(ContratanteRequestDTO dto);

    void updateFromDto(ContratanteUpdateDTO dto, @MappingTarget Contratante entity);


    ContratanteResponseDTO toDto(Contratante contratante);

    ContratanteResponseDTO OptionalToDto(Optional<Contratante> contratante);

}
