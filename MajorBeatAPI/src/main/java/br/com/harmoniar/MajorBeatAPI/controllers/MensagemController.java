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

    public ResponseEntity<List<MensagemResponseDTO>> getMensagensByIdUsuarioAutenticado(String token){
        return ResponseEntity.ok(services.listarMensagensByUsuarioAutenticado(token));
    }

    @GetMapping("/GetByMusicoId/{id}")
    public ResponseEntity<List<MensagemResponseDTO>> getAllMensagensByIdMusico(@PathVariable Long id){
        return ResponseEntity.ok(services.listarMensagensByIdMusico(id));
    }

    @GetMapping("/GetByContratanteId/{id}")
    public ResponseEntity<List<MensagemResponseDTO>> getAllMensagensByIdContratante(@PathVariable Long id){
        return ResponseEntity.ok(services.listarMensagensByIdContratante(id));
    }


    //Métodos POST
    @PostMapping("/enviarMensagemComum")
    public ResponseEntity<MensagemResponseDTO> enviarMensagem(@RequestBody MensagemRequestDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer ", "");
            return ResponseEntity.ok(services.enviarMensagem(dto, token));
    }


    //Métodos DELETE
    @DeleteMapping("/DeleteById/{id}")
    public ResponseEntity<MensagemResponseDTO> excluirMensagemById(@PathVariable Long id){
        if (services.deleteMensagemById(id) == true){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


}
