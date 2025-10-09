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

    @GetMapping("/getAllContratantes")
    public ResponseEntity<List<ContratanteResponseDTO>> getAllContratantes(){
            return ResponseEntity.ok(services.getAllContratantes());
    }

    @GetMapping("/getByTipoContratante/{tipoContratante}")
    public ResponseEntity<List<ContratanteResponseDTO>> getContratanteByTipoContratante(@PathVariable TipoContratante tipoContratante){
            return ResponseEntity.ok(services.getContratanteByTipoContratante(tipoContratante));
    }
    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteByNome(@PathVariable String nome) {
            return ResponseEntity.ok(services.getContratanteByNome(nome));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteById(@PathVariable Long id){
            return ResponseEntity.ok(services.getContratanteById(id));
    }

    //POST
    @PostMapping("/cadastrar")
    public ResponseEntity<ContratanteResponseDTO> cadastrarContratante(@RequestBody ContratanteRequestDTO dto){
            return ResponseEntity.ok(services.cadastrarContratante(dto));
    }

    @PostMapping("/login")
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

    @PutMapping("/editContratanteById")
    public ResponseEntity<ContratanteResponseDTO> editContratanteById(ContratanteUpdateDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer", "");
            return ResponseEntity.ok(services.editContratanteById(dto, token));
    }

    //PATCH
    @PatchMapping("/addMedia")
    public ResponseEntity<Void> adicionarMediaUrl(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.adicionarMediaUrl(token, dto);
        return ResponseEntity.ok().build();
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteContratanteById(@RequestHeader("Authorization" ) String authHeader){
        String token = authHeader.replace("Bearer", "");
        if(services.DeleteContratanteById(token) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMedia")
    public ResponseEntity<Void> deleteMedia(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.DeleteMediaUrl(token, dto);
        return ResponseEntity.ok().build();
    }

}
