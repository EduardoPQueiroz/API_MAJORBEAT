package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.GeneroMusicalResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.GeneroMusical;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMusicalMapper {
    GeneroMusicalMapper INSTANCE = Mappers.getMapper(GeneroMusicalMapper.class);
    List<GeneroMusicalResponseDTO> toResponseDTOList(List<GeneroMusical> generosMusicais);
}
