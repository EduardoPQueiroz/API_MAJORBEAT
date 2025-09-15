package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.MensagemRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MensagemResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Mensagem;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.mappers.MensagemMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MensagemRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemServices {

    @Autowired
    MensagemRepository repository;

    @Autowired
    MusicoRepository musicoRepository;

    @Autowired
    ContratanteRepository contratanteRepository;

    @Autowired
    MensagemMapper mapper;



    //Métodos GET

    public List<MensagemResponseDTO> listarMensagensByUsuarioAutenticado(String token){
        Long idUser = JwtUtil.extrairUsuarioId(token);
        Optional<Musico> musicoOptional = musicoRepository.findById(idUser);
        if (musicoOptional.isPresent()){
            List<Mensagem> mensagens = repository.findAllByIdMusico_IdMusico(idUser);
            return mapper.toResponseDTOList(mensagens);
        }
        Optional<Contratante> contratanteOptional = contratanteRepository.findById(idUser);
        if (contratanteOptional.isPresent()){
            List<Mensagem> mensagens = repository.findAllByIdContratante_IdContratante(idUser);
            return mapper.toResponseDTOList(mensagens);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mensagem foi encontrada.");
        }
    }

    public List<MensagemResponseDTO> listarMensagensByIdMusico(Long idMusico){
        Optional<Musico> existe = musicoRepository.findById(idMusico);
        if(existe.isPresent()){
            List<Mensagem> mensagens = repository.findAllByIdMusico_IdMusico(idMusico);
            if(!mensagens.isEmpty()){
                return mapper.toResponseDTOList(mensagens);
            }
            else {
                throw new NullPointerException("Não foram encontradas mensagens desse usuário");
            }
        }
        else{
            throw new EntityNotFoundException("Não foi encontrado um músico com esse id");
        }
    }

    public List<MensagemResponseDTO> listarMensagensByIdContratante(Long idContratante){
        Optional<Contratante> existe = contratanteRepository.findById(idContratante);
        if (existe.isPresent()){
            List<Mensagem> mensagens = repository.findAllByIdContratante_IdContratante(idContratante);
            if (!mensagens.isEmpty()){
                return mapper.toResponseDTOList(mensagens);
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontradas mensagens relacionadas a esse usuário");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um contratante com esse id");
        }
    }


    //Métodos POST
    public  MensagemResponseDTO enviarMensagem(MensagemRequestDTO dto, String token){

        Long idRemetente = JwtUtil.extrairUsuarioId(token);

        Mensagem entity = mapper.toEntity(dto);

        if (!entity.isProposta()){
            if (entity.getTexto().length() > 256){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de caracteres máximo superado");
            }

            entity.setIdRemetente(idRemetente);
            entity.setDataEnvio(LocalDateTime.now());
            Mensagem saved = repository.save(entity);
            return mapper.toDto(saved);
        }
        else {
            if(entity.getEvento().equals(null)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A proposta precisa estar atrelada a um evento!");
            } else if (entity.getValor() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor inferior a 0 informado");
            }
            entity.setIdRemetente(idRemetente);
            entity.setDataEnvio(LocalDateTime.now());
            Mensagem saved = repository.save(entity);
            return mapper.toDto(saved);
        }

    }

    //Métodos DELETE
    public boolean deleteMensagemById(Long id){
        Optional<Mensagem> mensagem = repository.findById(id);
        if (mensagem.isPresent()){
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível deletar uma entidade não existente");
        }
    }




}
