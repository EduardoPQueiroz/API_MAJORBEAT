package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;

public record ContratanteUpdateDTO(
        String nome,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        String links,
        String nomeEmpresa
) {
}
