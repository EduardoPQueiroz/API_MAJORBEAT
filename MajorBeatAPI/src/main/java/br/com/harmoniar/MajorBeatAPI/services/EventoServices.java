package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.StatusEvento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.EventoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServices {

    @Autowired
    EventoRepository repository;
    @Autowired
    EventoMapper mapper;

    //Métodos GET
    public List<EventoResponseDTO> getAllEventos(){

        try {
            return mapper.toResponseDTOList(repository.findAll());
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<EventoResponseDTO> getEventosByTipoMusico(TipoMusico tipoMusico){
        try{
            return mapper.toResponseDTOList(repository.findEventoByTipoMusico(tipoMusico));
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<EventoResponseDTO> getEventosByInstrumento(NomeInstrumento instrumento){
        try{
            return mapper.toResponseDTOList(repository.findByNomeInstrumentoContaining(instrumento));
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<EventoResponseDTO> getEventosByGenero(NomeGenero genero){
       try{
           return mapper.toResponseDTOList(repository.findByNomeGeneroContaining(genero));
       }catch(ResponseStatusException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }

    public EventoResponseDTO getEventoByNome(String nome){
        Optional<Evento> evento = repository.findByNome(nome);
        if (evento.isPresent()){
            return mapper.OptionaltoDto(evento);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um Evento criado com esse nome");
        }
    }

    public EventoResponseDTO getEventoById(Long id){
        Optional<Evento> evento = repository.findById(id);
        if (evento.isPresent()){
            return mapper.OptionaltoDto(evento);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado.");
        }
    }

    public List<EventoResponseDTO> getEventosByEndereco(String endereco){
        return mapper.toResponseDTOList(repository.findByEndereco(endereco));
    }
    public List<EventoResponseDTO> getEventosByData(LocalDate data){
        return mapper.toResponseDTOList(repository.findByData(data));
    }


    //MÉTODOS POST
    public EventoResponseDTO criarEvento(EventoRequestDTO dto){
        Evento entity = mapper.toEntity(dto);
        if (entity.getHoraInicio().isAfter(entity.getHoraFim())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento precisa começar antes de terminar!");
        }
        else{
            entity.setStatus(StatusEvento.NAO_PREENCHIDO);
            Evento saved  = repository.save(entity);
            return mapper.toDto(saved);
        }
    }

    //Métodos PUT
    public EventoResponseDTO alterarEvento(EventoUpdateDTO dto, Long id){
        Evento evento = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id de evento não encontrado."));
        if (evento.getHoraInicio().isAfter(evento.getHoraFim())) {
            mapper.updateFromDto(dto, evento);
            Evento saved = repository.save(evento);
            return mapper.toDto(saved);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o evento precisa começar antes de terminar");
        }
    }



    //Métodos DELETE
    public boolean excluirEvento(Long id){
        Optional<Evento> evento = repository.findById(id);
        if (evento.isPresent()){
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não pode excluir um evento inexistente!");
        }
    }


}
