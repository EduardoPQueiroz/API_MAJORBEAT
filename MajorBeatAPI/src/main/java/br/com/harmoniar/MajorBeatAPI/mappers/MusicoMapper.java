package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.MusicoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusicoMapper {
    MusicoMapper INSTANCE = Mappers.getMapper(MusicoMapper.class);
    List<MusicoResponseDTO> toResponseDTOList(List<Musico> musicos);
}
