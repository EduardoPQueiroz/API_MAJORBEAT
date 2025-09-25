package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.MensagemRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MensagemResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Mensagem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MensagemMapper {
    MensagemMapper INSTANCE = Mappers.getMapper(MensagemMapper.class);
    List<MensagemResponseDTO> toResponseDTOList(List<Mensagem> mensagens);

    Mensagem toEntity(MensagemRequestDTO dto);
    MensagemResponseDTO toDto(Mensagem mensagem);
}
