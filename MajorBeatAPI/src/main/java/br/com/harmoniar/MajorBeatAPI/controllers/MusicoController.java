package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
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
    @GetMapping("/getAllMusicos")
    public ResponseEntity<List<MusicoResponseDTO>> getAll(){
        return ResponseEntity.ok(services.getAllMusicos());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MusicoResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(services.getMusicoById(id));
    }

    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<MusicoResponseDTO> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(services.getMusicoByNome(nome));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<MusicoResponseDTO> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(services.getMusicoByEmail(email));
    }

    @GetMapping("getByGenero/{genero}")
    public ResponseEntity<List<MusicoResponseDTO>> getByGenero(@PathVariable NomeGenero genero){
        return ResponseEntity.ok(services.getMusicoByGenero(genero));
    }

    @GetMapping("getByInstrumento/{instrumento}")
    public ResponseEntity<List<MusicoResponseDTO>> getByInstrumento(@PathVariable NomeInstrumento instrumento){
        return ResponseEntity.ok(services.getMusicoByInstrumento(instrumento));
    }

    @GetMapping("/getByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<MusicoResponseDTO>> getMusicoByTipoMusico(@PathVariable TipoMusico tipoMusico){
        return ResponseEntity.ok(services.getMusicoByTipoMusico(tipoMusico));
    }

    //Post
    @PostMapping("/cadastrar")
    public ResponseEntity<MusicoResponseDTO> cadastrarMusico(@RequestBody MusicoRequestDTO dto){
        return ResponseEntity.ok(services.cadastrarMusico(dto));
    }

    //Autenticar Músico...
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginMusico(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            String token = services.autenticarMusico(loginRequestDTO.nome(), loginRequestDTO.email(), loginRequestDTO.senha());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch(HttpClientErrorException.Unauthorized e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    //Put
    @PutMapping("/editById/{id}")
    public ResponseEntity<MusicoResponseDTO> editMusicoById(@RequestBody MusicoUpdateDTO dto, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        return ResponseEntity.ok(services.editMusicoById(dto, token));
    }

    //Patch
    @PatchMapping("/addMedia")
    public ResponseEntity<Void> adicionarMediaUrl(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.adicionarMediaUrl(token, dto);
        return ResponseEntity.ok().build();
    }

    //Delete
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMusicoById(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        if(services.deleteMusicoById(token) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMedia")
    public ResponseEntity<Void> deleteMedia(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.deleteMediaUrl(dto, token);
        return ResponseEntity.ok().build();
    }

}
