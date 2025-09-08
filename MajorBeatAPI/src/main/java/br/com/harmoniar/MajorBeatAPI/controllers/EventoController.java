package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
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

    @GetMapping("/GetAll")
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos(){
            return ResponseEntity.ok(services.getAllEventos());
    }

    @GetMapping("/GetByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByTipoMusico(@PathVariable TipoMusico tipoMusico){
            return ResponseEntity.ok(services.getEventosByTipoMusico(tipoMusico));
    }

    @GetMapping("/GetByData/{data}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByData(@PathVariable LocalDate data){
            return ResponseEntity.ok(services.getEventosByData(data));
    }

    @GetMapping("/GetByEndereco/{endereco}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByEndereco(@PathVariable String endereco){
            return ResponseEntity.ok(services.getEventosByEndereco(endereco));
    }

    @GetMapping("/GetByNome/{nome}")
    public ResponseEntity<EventoResponseDTO> getEventoByNome(@PathVariable String nome){
            return ResponseEntity.ok(services.getEventoByNome(nome));
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id){
            return ResponseEntity.ok(services.getEventoById(id));
    }

    //Métodos POST

    @PostMapping("/criarEvento")
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO dto){
            return ResponseEntity.ok(services.criarEvento(dto));
    }

    //Métodos PUT
    @PutMapping("/atualizarEvento/{id}")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@RequestBody EventoUpdateDTO dto, @PathVariable Long id){
            return ResponseEntity.ok(services.alterarEvento(dto, id));
    }


    //Métodos DELETE

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteEventoById(@PathVariable Long id){
        if (services.excluirEvento(id) == true){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }





}
