package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.mappers.AvaliacaoMapper;
import br.com.harmoniar.MajorBeatAPI.services.AvaliacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AvaliacaoController {
    @Autowired
    AvaliacaoServices services;

    @Autowired
    AvaliacaoMapper mapper;

    @PostMapping
    public AvaliacaoResponseDTO avaliar(@RequestBody AvaliacaoResponseDTO dto){
        return services.avaliar(dto);
    }
}
