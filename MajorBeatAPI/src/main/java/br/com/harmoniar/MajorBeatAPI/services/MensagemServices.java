package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.MensagemResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Mensagem;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.mappers.MensagemMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MensagemRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                throw new EntityNotFoundException("Não foram encontradas mensagens relacionadas a esse usuário");
            }
        }
        else{
            throw new EntityNotFoundException(("Não foi encontrado um contratante com esse id"));
        }
    }


    //Métodos POST
    public  MensagemResponseDTO enviarMensagem(MensagemResponseDTO dto){
        Mensagem entity = mapper.toEntity(dto);
        if (entity.getTexto().length() > 256){
            throw new IllegalArgumentException("Número de caracteres máximo superado");
        }
        Mensagem saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    //Métodos PUT
    public MensagemResponseDTO editarMensagem(MensagemResponseDTO dto, Long id){
        Optional<Mensagem> existe = repository.findById(id);
        if (existe.isPresent()){
            Mensagem entity = mapper.toEntity(dto);
            if (entity.getTexto().length() > 256){
                throw new IllegalArgumentException("Número de caracteres máximo superado");
            }
            Mensagem saved = repository.save(entity);
            return mapper.toDto(saved);
        }else {
            throw new EntityNotFoundException(("Impossível editar uma mensagem que não existe"));
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
            throw new EntityNotFoundException("Não é possível deletar uma entidade não existente");
        }
    }




}
