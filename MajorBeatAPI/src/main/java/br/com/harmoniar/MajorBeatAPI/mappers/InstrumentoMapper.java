package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.InstrumentoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Instrumento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstrumentoMapper {
    InstrumentoMapper INSTANCE = Mappers.getMapper(InstrumentoMapper.class);
    List<InstrumentoResponseDTO> toResponseDTOList(List<Instrumento> instrumentos);
}
