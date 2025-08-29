package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.MusicoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.LoginRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.LoginResponseDTO;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.MusicoMapper;
import br.com.harmoniar.MajorBeatAPI.services.MusicoServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Musico")
public class MusicoController {
    @Autowired
    MusicoServices services;

    @Autowired
    MusicoMapper mapper;

    //Get
    @GetMapping("/GetAllMusicos")
    public ResponseEntity<List<MusicoResponseDTO>> getAllMusicos(){
        try{
            return ResponseEntity.ok(services.getAllMusicos());
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicoResponseDTO> getMusicoById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(services.getMusicoById(id));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByNome/{nome}")
    public ResponseEntity<MusicoResponseDTO> getMusicoByNome(@PathVariable String nome){
        try{
            return ResponseEntity.ok(services.getMusicoByNome(nome));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<MusicoResponseDTO>> getMusicoByTipoMusico(@PathVariable TipoMusico tipoMusico){
        try{
            return ResponseEntity.ok(services.getMusicoByTipoMusico(tipoMusico));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Post
    @PostMapping("/cadastrarMusico")
    public ResponseEntity<MusicoResponseDTO> cadastrarMusico(@RequestBody MusicoResponseDTO dto){
        try{
            return ResponseEntity.ok(services.cadastrarMusico(dto));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
    //Autenticar Músico...

    //Put
    @PutMapping("/editMusicoById/{id}")
    public ResponseEntity<MusicoResponseDTO> editMusicoById(@RequestBody MusicoResponseDTO dto, @PathVariable Long id){
        try {
            return ResponseEntity.ok(services.editMusicoById(dto, id));
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusicoById(@PathVariable Long id){
        if(services.deleteMusicoById(id) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
