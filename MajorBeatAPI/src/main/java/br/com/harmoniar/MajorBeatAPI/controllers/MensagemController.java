package br.com.harmoniar.MajorBeatAPI.controllers;

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

    @GetMapping("/GetByMusicoId/{id}")
    public ResponseEntity<List<MensagemResponseDTO>> getAllMensagensByIdMusico(@PathVariable Long id){
        try {
            return ResponseEntity.ok(services.listarMensagensByIdMusico(id));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByContratanteId/{id}")
    public ResponseEntity<List<MensagemResponseDTO>> getAllMensagensByIdContratante(@PathVariable Long id){
        try {
            return ResponseEntity.ok(services.listarMensagensByIdContratante(id));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


    //Métodos POST
    @PostMapping("/enviarMensagem")
    public ResponseEntity<MensagemResponseDTO> enviarMensagem(@RequestBody MensagemResponseDTO dto){
        try {
            return ResponseEntity.ok(services.enviarMensagem(dto));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //Métodos PUT

    @PutMapping("/editarMensagemById/{id}")
    public ResponseEntity<MensagemResponseDTO> editarMensagem(@RequestBody MensagemResponseDTO dto, @PathVariable Long id){
        try{
            return ResponseEntity.ok(services.editarMensagem(dto, id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
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
