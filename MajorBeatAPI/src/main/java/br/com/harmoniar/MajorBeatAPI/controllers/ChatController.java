package br.com.harmoniar.MajorBeatAPI.controllers;


import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.services.ChatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Chat")
public class ChatController {

    @Autowired
    private ChatServices services;

    @GetMapping("/GetChats")
    public ResponseEntity<List<ChatResponseDTO>> listarChatsPorIdUsuarioAutenticado(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(services.listarChatsUsuarioAutenticado(token));
    }


}
