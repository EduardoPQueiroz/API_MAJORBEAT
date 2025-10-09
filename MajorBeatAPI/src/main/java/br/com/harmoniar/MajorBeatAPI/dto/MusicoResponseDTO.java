package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MusicoResponseDTO(
         Long idMusico,
         String nome,
         String apelido,
         String email,
         String telefone,
         String endereco,
         byte[] fotoPerfil,
         String biografia,
         LocalDate dtCriacao,
         List<String> links,
         TipoMusico tipoMusico,
         List<NomeInstrumento> nomeInstrumento,
         List<NomeGenero> nomeGenero,
         Role role,
         List<String> mediaUrl,

         List<AvaliacaoResponseDTO> avaliacoes,
         List<ChatResponseDTO> chats
) {
}
