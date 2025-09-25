package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.mappers.AvaliacaoMapper;
import br.com.harmoniar.MajorBeatAPI.services.AvaliacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoServices services;

    @Autowired
    AvaliacaoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> getAvaliacoesByIdUsuario(@PathVariable Long id){
        return ResponseEntity.ok(services.getAvaliacoesById(id));
    }

    @GetMapping("/getMedias/{id}")
    public ResponseEntity<Double> getMediaAvaliacoesByIdUsuario(@PathVariable Long id){
        return ResponseEntity.ok(services.getMediaAvaliacaoByIdUsuario(id));
    }

    @PostMapping("/avaliar")
    public ResponseEntity<AvaliacaoResponseDTO> avaliar(
            @RequestBody AvaliacaoRequestDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        AvaliacaoResponseDTO avaliacao = services.avaliar(dto, token);

        return ResponseEntity.ok(avaliacao);
    }



}
