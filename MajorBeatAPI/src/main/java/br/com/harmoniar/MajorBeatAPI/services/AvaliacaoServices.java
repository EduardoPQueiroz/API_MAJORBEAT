package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
import br.com.harmoniar.MajorBeatAPI.mappers.AvaliacaoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServices {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private AvaliacaoMapper mapper;

    public AvaliacaoResponseDTO avaliar(AvaliacaoResponseDTO dto){
        if(dto.nota()<= 3 && dto.comentario().isBlank()){
            throw new IllegalArgumentException("Uma nota inferior a três estrelas deve obrigatoriamente conter um comentário");
        }

        Avaliacao entity = mapper.toEntity(dto);
        Avaliacao saved = repository.save(entity);
        return mapper.toDto(saved);
    }
}
