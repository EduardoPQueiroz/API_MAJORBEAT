package br.com.harmoniar.MajorBeatAPI.controllers;


import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.services.ChatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Chat")
public class ChatController {

    @Autowired
    private ChatServices services;

    @GetMapping
    public List<ChatResponseDTO> listarChats(){
        return services.listarChats();
    }

    @GetMapping("/GetByContratanteId/{id}")
    public List<ChatResponseDTO> listarChatsporIdContratante(@PathVariable Long id){
        return  services.listarChatsPorContratanteId(id);
    }

}
