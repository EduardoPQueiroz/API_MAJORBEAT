package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.PagamentoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);
    List<PagamentoResponseDTO> toResponseDTOList(List<Pagamento> pagamentos);
}
