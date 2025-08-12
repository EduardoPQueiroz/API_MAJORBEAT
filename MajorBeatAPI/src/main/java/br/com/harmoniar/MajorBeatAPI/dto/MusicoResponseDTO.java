package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MusicoResponseDTO(
        Long id,
        String nome,
        String apelido,
        String email,
        String senha,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        LocalDate dtCriacao,
        String links,
        String cpf,
        String generoPrincipal,
        TipoMusico tipoMusico
) {
}
