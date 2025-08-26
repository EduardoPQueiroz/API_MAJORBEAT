package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.mappers.ChatMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    

}
