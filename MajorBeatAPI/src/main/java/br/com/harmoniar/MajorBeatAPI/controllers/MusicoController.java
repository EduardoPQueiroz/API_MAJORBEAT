package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.MusicoMapper;
import br.com.harmoniar.MajorBeatAPI.services.MusicoServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
        return ResponseEntity.ok(services.getAllMusicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicoResponseDTO> getMusicoById(@PathVariable Long id){
        return ResponseEntity.ok(services.getMusicoById(id));
    }

    @GetMapping("/GetByNome/{nome}")
    public ResponseEntity<MusicoResponseDTO> getMusicoByNome(@PathVariable String nome){
        return ResponseEntity.ok(services.getMusicoByNome(nome));
    }

    @GetMapping("/GetByEmail/{email}")
    public ResponseEntity<MusicoResponseDTO> getMusicoByEmail(@PathVariable String email){
        return ResponseEntity.ok(services.getMusicoByEmail(email));
    }

    @GetMapping("/GetByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<MusicoResponseDTO>> getMusicoByTipoMusico(@PathVariable TipoMusico tipoMusico){
        return ResponseEntity.ok(services.getMusicoByTipoMusico(tipoMusico));
    }

    //Post
    @PostMapping("/cadastrarMusico")
    public ResponseEntity<MusicoResponseDTO> cadastrarMusico(@RequestBody MusicoRequestDTO dto){
        return ResponseEntity.ok(services.cadastrarMusico(dto));
    }
    //Autenticar Músico...
    public ResponseEntity<LoginResponseDTO> loginMusico(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            String token = services.autenticarMusico(loginRequestDTO.nome(), loginRequestDTO.email(), loginRequestDTO.senha());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch(HttpClientErrorException.Unauthorized e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    //Put
    @PutMapping("/editMusicoById/{id}")
    public ResponseEntity<MusicoResponseDTO> editMusicoById(@RequestBody MusicoUpdateDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(services.editMusicoById(dto, id));
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
