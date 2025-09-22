package br.com.harmoniar.MajorBeatAPI.controllers;


import br.com.harmoniar.MajorBeatAPI.dto.ChatRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.services.ChatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Chat")
public class ChatController {

    @Autowired
    private ChatServices services;

    @GetMapping("/getChats")
    public ResponseEntity<List<ChatResponseDTO>> listarChatsPorIdUsuarioAutenticado(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(services.listarChatsUsuarioAutenticado(token));
    }

    @PostMapping
    public ResponseEntity<ChatResponseDTO> criarChat(@RequestBody ChatRequestDTO dto) {
        ChatResponseDTO responseDTO = services.criarChat(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity deleteChatById(@PathVariable Long id){
        try {
            services.deleteChatById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }



}
