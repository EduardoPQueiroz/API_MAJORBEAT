package br.com.harmoniar.MajorBeatAPI.dto;

public record LoginRequestDTO(
         String nome,
         String email,
         String senha
) {
}
