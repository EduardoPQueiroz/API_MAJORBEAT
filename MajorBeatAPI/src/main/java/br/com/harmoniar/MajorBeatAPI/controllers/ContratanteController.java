package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import br.com.harmoniar.MajorBeatAPI.mappers.ContratanteMapper;
import br.com.harmoniar.MajorBeatAPI.services.ContratanteServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Contratante")
public class ContratanteController {
    @Autowired
    ContratanteServices services;

    @Autowired
    ContratanteMapper mapper;

    //GET

    @GetMapping("/GetAllContratantes")
    public ResponseEntity<List<ContratanteResponseDTO>> getAllContratantes(){
        try{
            return ResponseEntity.ok(services.getAllContratantes());
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByTipoContratante/{tipoContratante}")
    public ResponseEntity<List<ContratanteResponseDTO>> getContratanteByTipoContratante(@PathVariable TipoContratante tipoContratante){
        try{
            return ResponseEntity.ok(services.getContratanteByTipoContratante(tipoContratante));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByNome/{nome}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteByNome(@PathVariable String nome){
        try{
            return ResponseEntity.ok(services.getContratanteByNome(nome));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(services.getContratanteById(id));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping("/cadastrarContratante")
    public ResponseEntity<ContratanteResponseDTO> cadastrarContratante(@RequestBody ContratanteRequestDTO dto){
        return ResponseEntity.ok(services.cadastrarContratante(dto));
    }

    @PostMapping("/autenticarContratante")
    public ResponseEntity<LoginResponseDTO> LoginContratante(@RequestBody LoginRequestDTO login){
        try{
            String token = services.autenticarContratante(login.nome(), login.email(), login.senha());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    //POST

    @PutMapping("/editContratanteById/{id}")
    public ResponseEntity<ContratanteResponseDTO> editContratanteById(ContratanteUpdateDTO dto, Long id){
            return ResponseEntity.ok(services.editContratanteById(dto, id));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContratanteById(@PathVariable Long id){
        if(services.DeleteContratanteById(id) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
