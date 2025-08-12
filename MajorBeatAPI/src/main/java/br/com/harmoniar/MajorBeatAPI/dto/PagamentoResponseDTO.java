package br.com.harmoniar.MajorBeatAPI.dto;

import java.time.LocalDateTime;

public record PagamentoResponseDTO(
        Long id,
        float valor,
        LocalDateTime dataPagamento,
        String formaPagamento,
        boolean status
) {
}
