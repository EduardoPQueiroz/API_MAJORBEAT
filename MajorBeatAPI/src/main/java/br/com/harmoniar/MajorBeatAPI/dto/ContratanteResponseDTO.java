package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContratanteResponseDTO(
        Long id,
        String nome,
        String senha,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        LocalDate dtCriacao,
        String links,
        String nomeEmpresa,
        String cpf,
        String cnpj,
        TipoContratante tipoContratante
) {
}
