package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.mappers.ChatMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServices {

    @Autowired
    private ChatRepository repository;

    @Autowired
    private ChatMapper mapper;


    public List<ChatResponseDTO> listarChats(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public List<ChatResponseDTO> listarChatsPorContratanteId(Long id){
        List<Chat> chats = repository.findAllByIdContratante_IdContratante(id);
        return mapper.toResponseDTOList(chats);
    }

    public List<ChatResponseDTO> listarChatsPorMusicoId(Long id){
        List<Chat> chats = repository.findAllByIdMusico_IdMusico(id);
        return mapper.toResponseDTOList(chats);
    }

}
