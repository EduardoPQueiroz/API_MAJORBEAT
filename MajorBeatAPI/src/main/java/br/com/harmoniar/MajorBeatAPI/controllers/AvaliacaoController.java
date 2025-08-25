package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.mappers.AvaliacaoMapper;
import br.com.harmoniar.MajorBeatAPI.services.AvaliacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AvaliacaoController {
    @Autowired
    AvaliacaoServices services;

    @Autowired
    AvaliacaoMapper mapper;

    @GetMapping
    public List<AvaliacaoResponseDTO> listarAvaliacao(){
        return services.listarAvaliacoes();
    }

    @PostMapping
    public AvaliacaoResponseDTO avaliar(@RequestBody AvaliacaoResponseDTO dto){
        return services.avaliar(dto);
    }
}
