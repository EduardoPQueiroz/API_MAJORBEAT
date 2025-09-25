package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;

import java.util.List;

public record MusicoUpdateDTO(
        String nome,
        String apelido,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        String links,
        List<NomeInstrumento> nomeInstrumentos,
        List<NomeGenero> nomeGeneros
) {
}
