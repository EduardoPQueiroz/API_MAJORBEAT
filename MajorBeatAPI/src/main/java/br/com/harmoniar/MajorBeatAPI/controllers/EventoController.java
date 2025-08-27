package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
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

@RestController("/Eventos")
public class EventoController {
    @Autowired
    EventoServices services;
    @Autowired
    EventoMapper mapper;

    @GetMapping("/GetAll")
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos(){
        try{
            return ResponseEntity.ok(services.getAllEventos());
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByTipoMusico(@PathVariable TipoMusico tipoMusico){
        try {
            return ResponseEntity.ok(services.getEventosByTipoMusico(tipoMusico));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByData/{data}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByData(@PathVariable LocalDate data){
        try{
            return ResponseEntity.ok(services.getEventosByData(data));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByEndereco/{endereco}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByEndereco(@PathVariable String endereco){
        try{
            return ResponseEntity.ok(services.getEventosByEndereco(endereco));
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetByNome/{nome}")
    public ResponseEntity<EventoResponseDTO> getEventoByNome(@PathVariable String nome){
        try{
            return ResponseEntity.ok(services.getEventoByNome(nome));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(services.getEventoById(id));
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Métodos POST

    @PostMapping("/criarEvento")
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoResponseDTO dto){
        try{
            return ResponseEntity.ok(services.criarEvento(dto));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //Métodos PUT
    @PutMapping("/atualizarEvento/{id}")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@RequestBody EventoResponseDTO dto, @PathVariable Long id){
        try{
            return ResponseEntity.ok(services.alterarEvento(dto, id));
        }catch (NullPointerException e){
            return ResponseEntity.notFound().build();
        }
    }


    //Métodos DELETE

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<EventoResponseDTO> deleteEventoById(@PathVariable Long id){
        if (services.excluirEvento(id) == true){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }





}
