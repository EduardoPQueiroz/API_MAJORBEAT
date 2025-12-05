package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.PropostaRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.PropostaResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.entity.Proposta;
import br.com.harmoniar.MajorBeatAPI.enums.StatusProposta;
import br.com.harmoniar.MajorBeatAPI.mappers.PropostaMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.PropostaRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PropostaServices {

    @Autowired
    PropostaRepository repository;

    @Autowired
    MusicoRepository musicoRepository;

    @Autowired
    ContratanteRepository contratanteRepository;
    @Autowired
    PropostaMapper mapper;


    //GET
    public List<PropostaResponseDTO> getAll(){
        try {
            return mapper.toResponseDTOList(repository.findAll());
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Propostas não encontradas");
        }
    }

    public PropostaResponseDTO getById(Long id){
        Proposta proposta = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada"));
        return mapper.toDto(proposta);
    }

    public List<PropostaResponseDTO> getByMusicoId(Long idMusico){
        Musico musico = musicoRepository.findById(idMusico).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        try {
            return mapper.toResponseDTOList(repository.findByMusico(musico));
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Propostas não encontradas");
        }
    }

    public List<PropostaResponseDTO> getByContratanteId(Long idContratante){
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        try {
            return mapper.toResponseDTOList(repository.findByContratante(contratante));
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Propostas não encontradas");
        }
    }

    //POST
    public PropostaResponseDTO postProposta(PropostaRequestDTO dto, String token) {

        Proposta entity = mapper.toEntity(dto);

        Long idUsuarioLogado = JwtUtil.extrairUsuarioId(token);
        entity.setIdRemetente(idUsuarioLogado);
        entity.setStatusProposta(StatusProposta.ABERTO);
        entity.setDataEnvio(LocalDate.now());

        boolean isContratante = contratanteRepository.existsById(idUsuarioLogado);
        boolean isMusico = musicoRepository.existsById(idUsuarioLogado);

        if (isContratante) {
            entity.setContratante(contratanteRepository.findById(idUsuarioLogado).get());
            entity.setMusico(null);

        } else if (isMusico) {
            entity.setMusico(musicoRepository.findById(idUsuarioLogado).get());
        } else {
            throw new IllegalArgumentException("Usuário remetente não encontrado.");
        }

        Proposta saved = repository.save(entity);

        return mapper.toDto(saved);
    }


}
