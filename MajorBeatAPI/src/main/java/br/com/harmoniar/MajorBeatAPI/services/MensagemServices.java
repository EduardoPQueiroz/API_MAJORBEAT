package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.MensagemRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MensagemResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Mensagem;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.mappers.MensagemMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ChatRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MensagemRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
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
    ChatRepository chatRepository;

    @Autowired
    ContratanteRepository contratanteRepository;

    @Autowired
    MensagemMapper mapper;



    //Métodos GET

    public List<MensagemResponseDTO> listarMensagensByUsuarioAutenticado(String token){
        Long idUser = JwtUtil.extrairUsuarioId(token);
        Optional<Musico> musicoOptional = musicoRepository.findById(idUser);
        if (musicoOptional.isPresent()){
            Musico musico = musicoOptional.get();
            List<Chat> chats = chatRepository.findAllByMusico(musico);
            List<Mensagem> mensagens = repository.findByChatIn(chats);
            return mapper.toResponseDTOList(mensagens);
        }
        Optional<Contratante> contratanteOptional = contratanteRepository.findById(idUser);
        if (contratanteOptional.isPresent()){
            Contratante contratante = contratanteOptional.get();
            List<Chat> chats = chatRepository.findAllByContratante(contratante);
            List<Mensagem> mensagens = repository.findByChatIn(chats);
            return mapper.toResponseDTOList(mensagens);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma mensagem foi encontrada.");
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
    public void deleteMensagemById(String token){
        Long id = JwtUtil.extrairUsuarioId(token);
        Optional<Mensagem> mensagem = repository.findById(id);
        if (mensagem.isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível deletar uma entidade não existente");
        }
    }




}
