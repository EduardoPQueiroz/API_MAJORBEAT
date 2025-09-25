package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;

import java.time.LocalDate;

public record ContratanteRequestDTO(
        String nome,
        String senha,
        String telefone,
        String email,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        String links,
        String nomeEmpresa,
        TipoContratante tipoContratante
) {
}
