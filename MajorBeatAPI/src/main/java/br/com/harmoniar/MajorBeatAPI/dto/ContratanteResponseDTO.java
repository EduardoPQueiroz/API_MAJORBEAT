package br.com.harmoniar.MajorBeatAPI.dto;

import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ContratanteResponseDTO(
        Long idContratante,
        String nome,
        String apelido,
        String email,
        String telefone,
        String endereco,
        byte[] fotoPerfil,
        String biografia,
        LocalDate dtCriacao,
        List<String> links,
        String nomeEmpresa,
        TipoContratante tipoContratante,
        Role role,
        List<String> mediaUrl,
        List<AvaliacaoResponseDTO> avaliacoes,
        List<ChatResponseDTO> chats
) {
}
