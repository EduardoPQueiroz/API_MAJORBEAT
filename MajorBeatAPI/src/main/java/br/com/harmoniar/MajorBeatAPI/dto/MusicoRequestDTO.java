package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;

import java.time.LocalDate;
import java.util.List;

public record MusicoRequestDTO(
        String nome,
        String apelido,
        String email,
        String senha,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        String links,

        List<NomeInstrumento> nomeInstrumentos,

        List<NomeGenero> nomeGeneros,

        TipoMusico tipoMusico
) {
}
