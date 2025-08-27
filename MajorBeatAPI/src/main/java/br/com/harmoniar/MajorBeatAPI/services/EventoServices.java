package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.EventoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.EventoRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return mapper.toResponseDTOList(repository.findAll());
    }

    public List<EventoResponseDTO> getEventosByTipoMusico(TipoMusico tipoMusico){
        return mapper.toResponseDTOList(repository.findEventoByTipoMusico(tipoMusico));
    }

    public List<EventoResponseDTO> getEventosByInstrumento(NomeInstrumento instrumento){
        return mapper.toResponseDTOList(repository.findByInstrumentosContaining(instrumento));
    }

    public List<EventoResponseDTO> getEventosByGenero(NomeGenero genero){
        return mapper.toResponseDTOList(repository.findByGeneroContaining(genero));
    }

    public EventoResponseDTO getEventoByNome(String nome){
        Optional<Evento> evento = repository.findByNome(nome);
        if (evento.isPresent()){
            return mapper.OptionaltoDto(evento);
        }
        else{
            throw new NullPointerException("Não existe um Evento criado com esse nome");
        }
    }

    public EventoResponseDTO getEventoById(Long id){
        Optional<Evento> evento = repository.findById(id);
        if (evento.isPresent()){
            return mapper.OptionaltoDto(evento);
        }
        else{
            throw new NullPointerException("Id inválido");
        }
    }

    public List<EventoResponseDTO> getEventosByEndereco(String endereco){
        return mapper.toResponseDTOList(repository.findByEndereco(endereco));
    }
    public List<EventoResponseDTO> getEventosByData(LocalDate data){
        return mapper.toResponseDTOList(repository.findByData(data));
    }


    //MÉTODOS POST
    public EventoResponseDTO criarEvento(EventoResponseDTO dto){
        Evento entity = mapper.toEntity(dto);
        if (entity.getHoraInicio().isAfter(entity.getHoraFim())){
            throw new IllegalArgumentException("O evento precisa começar antes de terminar!");
        }
        else{
            Evento saved  = repository.save(entity);
            return mapper.toDto(saved);
        }
    }

    //Métodos PUT
    public EventoResponseDTO alterarEvento(EventoResponseDTO dto, Long id){
        Evento evento = mapper.toEntity(dto);
        Optional<Evento> existe = repository.findById(id);
        if (existe.isPresent()){
            if (evento.getHoraInicio().isAfter(evento.getHoraFim())) {
                Evento saved = repository.save(evento);
                return mapper.toDto(saved);
            }
            else{
                throw new IllegalArgumentException("o evento precisa começar antes de terminar");
            }
        }
        else{
            throw new NullPointerException("Você não pode alterar um evento inexistente");
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
            throw new NullPointerException("Você não pode excluir um evento inexistente!");
        }
    }


}
