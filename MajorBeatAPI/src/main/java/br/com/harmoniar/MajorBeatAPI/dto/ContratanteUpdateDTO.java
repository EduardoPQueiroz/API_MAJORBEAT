package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;

import java.util.List;

public record ContratanteUpdateDTO(
        String nome,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        List<String> links,
        String nomeEmpresa
) {
}
