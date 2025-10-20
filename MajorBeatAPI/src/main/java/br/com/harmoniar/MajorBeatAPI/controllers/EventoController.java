package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.EventoMapper;
import br.com.harmoniar.MajorBeatAPI.services.EventoServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Eventos")
public class EventoController {
    @Autowired
    EventoServices services;
    @Autowired
    EventoMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos(){
            return ResponseEntity.ok(services.getAllEventos());
    }

    @GetMapping("/getByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByTipoMusico(@PathVariable TipoMusico tipoMusico){
            return ResponseEntity.ok(services.getEventosByTipoMusico(tipoMusico));
    }

    @GetMapping("/getByData/{data}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByData(@PathVariable LocalDate data){
            return ResponseEntity.ok(services.getEventosByData(data));
    }

    @GetMapping("/getByEndereco/{endereco}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByEndereco(@PathVariable String endereco){
            return ResponseEntity.ok(services.getEventosByEndereco(endereco));
    }

    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<EventoResponseDTO> getEventoByNome(@PathVariable String nome){
            return ResponseEntity.ok(services.getEventoByNome(nome));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id){
            return ResponseEntity.ok(services.getEventoById(id));
    }

    @GetMapping("/getByGenero/{nomeGenero}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByGenero(@PathVariable NomeGenero nomeGenero){
        return ResponseEntity.ok(services.getEventosByGenero(nomeGenero));
    }

    @GetMapping("/getByInstrumento/{instrumento}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByInstrumento(@PathVariable NomeInstrumento instrumento){
        return ResponseEntity.ok(services.getEventosByInstrumento(instrumento));
    }

    //Métodos POST

    @PostMapping("/criar")
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO dto){
            return ResponseEntity.ok(services.criarEvento(dto));
    }

    //Métodos PUT
    @PutMapping("/atualizar")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@RequestBody EventoUpdateDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer", "");
            return ResponseEntity.ok(services.alterarEvento(dto, token));
    }


    //Métodos DELETE

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEventoById(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        if (services.excluirEvento(token) == true){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }





}
