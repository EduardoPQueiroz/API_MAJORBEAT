package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.PropostaRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.PropostaResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.PropostaUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Proposta;
import br.com.harmoniar.MajorBeatAPI.services.PropostaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Proposta")
public class PropostaController {
    @Autowired
    PropostaServices services;
    @GetMapping("/getById/{id}")
    public ResponseEntity<PropostaResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(services.getById(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PropostaResponseDTO>> getAll(){
        return ResponseEntity.ok(services.getAll());
    }

    @GetMapping("/getByMusicoId/{idMusico}")
    public ResponseEntity<List<PropostaResponseDTO>> getByMusicoId(@PathVariable Long idMusico){
        return ResponseEntity.ok(services.getByMusicoId(idMusico));
    }

    @GetMapping("/getByContratanteId/{idContratante}")
    public ResponseEntity<List<PropostaResponseDTO>> getByContratanteId(@PathVariable Long idContratante){
        return ResponseEntity.ok(services.getByContratanteId(idContratante));
    }

    //POST
    @PostMapping("/post")
    public ResponseEntity<PropostaResponseDTO> postProposta(@RequestHeader("Authorization") String authHeader, @RequestBody PropostaRequestDTO dto){
        String token = authHeader.replace("Bearer ", "").trim();
        return ResponseEntity.ok(services.postProposta(dto, token));
    }

    //PUT
    @PutMapping("/update/{idProposta}")
    public ResponseEntity<PropostaResponseDTO> updateProposta(@RequestBody PropostaUpdateDTO dto, @PathVariable Long idProposta){
        return ResponseEntity.ok(services.updateProposta(dto, idProposta));
    }

}
