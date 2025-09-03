package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.MusicoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MusicoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MusicoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MusicoMapper {
    MusicoMapper INSTANCE = Mappers.getMapper(MusicoMapper.class);
    List<MusicoResponseDTO> toResponseDTOList(List<Musico> musicos);

    Musico toEntity(MusicoRequestDTO dto);
    void updateFromDto(MusicoUpdateDTO dto, @MappingTarget Musico entity);
    MusicoResponseDTO toDto(Musico musico);

    MusicoResponseDTO OptionalToDto(Optional<Musico> musico);
}
