package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.LoginRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.LoginResponseDTO;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import br.com.harmoniar.MajorBeatAPI.mappers.ContratanteMapper;
import br.com.harmoniar.MajorBeatAPI.services.ContratanteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/Contratante")
public class ContratanteController {
    @Autowired
    ContratanteServices services;

    @Autowired
    ContratanteMapper mapper;

    //GET

    @GetMapping("/GetAllContratantes")
    public List<ContratanteResponseDTO> getAllContratantes(){
        return services.getAllContratantes();
    }

    @GetMapping("/GetByTipoContratante/{tipoContratante}")
    public List<ContratanteResponseDTO> getContratanteByTipoContratante(@PathVariable TipoContratante tipoContratante){
        return services.getContratanteByTipoContratante(tipoContratante);
    }

    @GetMapping("/GetByNome/{nome}")
    public ContratanteResponseDTO getContratanteByNome(@PathVariable String nome){
        return services.getContratanteByNome(nome);
    }

    @GetMapping("/{id}")
    public ContratanteResponseDTO getContratanteById(@PathVariable Long id){
        return services.getContratanteById(id);
    }

    //POST
    @PostMapping
    public ContratanteResponseDTO cadastrarContratante(@RequestBody ContratanteResponseDTO dto){
        return services.cadastrarContratante(dto);
    }

    public ResponseEntity<LoginResponseDTO> LoginContratante(@RequestBody LoginRequestDTO login){
        try{
            String token = services.autenticarContratante(login.nome(), login.senha());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ContratanteResponseDTO> deleteContratanteById(@PathVariable Long id){
        if(services.DeleteContratanteById(id) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
