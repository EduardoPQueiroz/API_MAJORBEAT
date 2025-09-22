package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.MensagemRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MensagemResponseDTO;
import br.com.harmoniar.MajorBeatAPI.mappers.MensagemMapper;
import br.com.harmoniar.MajorBeatAPI.services.MensagemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Mensagem")
public class MensagemController {

    @Autowired
    MensagemServices services;

    @Autowired
    MensagemMapper mapper;

    //Métodos GET

    public ResponseEntity<List<MensagemResponseDTO>> getMensagensByIdUsuarioAutenticado(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(services.listarMensagensByUsuarioAutenticado(token));
    }



    //Métodos POST
    @PostMapping("/enviarMensagemComum")
    public ResponseEntity<MensagemResponseDTO> enviarMensagem(@RequestBody MensagemRequestDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer ", "");
            return ResponseEntity.ok(services.enviarMensagem(dto, token));
    }


    //Métodos DELETE
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<MensagemResponseDTO> excluirMensagemById(@PathVariable Long id){
         try {
             services.deleteMensagemById(id);
             return ResponseEntity.noContent().build();
         }catch (Exception e){
             return ResponseEntity.badRequest().build();
         }

    }


}
