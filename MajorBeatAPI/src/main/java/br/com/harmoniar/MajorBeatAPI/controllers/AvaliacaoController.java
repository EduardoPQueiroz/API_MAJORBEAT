package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
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

    @GetMapping
    public List<AvaliacaoResponseDTO> listarAvaliacao(){
        return services.listarAvaliacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> getAvaliacaoById(@PathVariable Long id){
        var existe = services.getAvaliacaoById(id);
        if(existe != null){
            return ResponseEntity.ok(existe);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
