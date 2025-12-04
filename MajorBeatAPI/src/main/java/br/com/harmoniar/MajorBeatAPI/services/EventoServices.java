package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MediaUrlRequestDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.*;
import br.com.harmoniar.MajorBeatAPI.mappers.EventoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.EventoRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    ContratanteRepository contratanteRepository;

    @Autowired
    MusicoRepository musicoRepository;
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

    public List<EventoResponseDTO> getEventosByTipoEvento(TipoEvento tipoEvento){
        try{
            return mapper.toResponseDTOList(repository.findEventoByTipoEvento(tipoEvento));
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<EventoResponseDTO> getEventosByInstrumento(NomeInstrumento instrumento){
        try{
            return mapper.toResponseDTOList(repository.findByInstrumentosContaining(instrumento));
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<EventoResponseDTO> getEventosByGenero(NomeGenero genero){
       try{
           return mapper.toResponseDTOList(repository.findByGenerosContaining(genero));
       }catch(ResponseStatusException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }

    public List<EventoResponseDTO> getEventosByContratanteId(Long idContratante){
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        try{
            return mapper.toResponseDTOList(repository.findByContratante(contratante));
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontrados eventos associados a esse contratante");
        }
    }

    public EventoResponseDTO getEventoByNome(String nome){
        Evento evento = repository.findByNome(nome).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Um Evento com esse NOME não encontrado"));
        return mapper.toDto(evento);
    }

    public EventoResponseDTO getEventoById(Long id){
        Evento evento = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Um Evento com esse ID não foi encontrado"));
        return mapper.toDto(evento);
    }

    public List<EventoResponseDTO> getEventosByEndereco(String endereco){
        return mapper.toResponseDTOList(repository.findByEndereco(endereco));
    }
    public List<EventoResponseDTO> getEventosByData(LocalDate data){
        return mapper.toResponseDTOList(repository.findByData(data));
    }


    //MÉTODOS POST
    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public EventoResponseDTO criarEvento(EventoRequestDTO dto, String token){
        Evento entity = mapper.toEntity(dto);
        Long idContratante = JwtUtil.extrairUsuarioId(token);
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        entity.setContratante(contratante);

        if (entity.getHoraInicio().isAfter(entity.getHoraFim())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento precisa começar antes de terminar!");
        }
        else{
            try {
                Evento saved  = repository.save(entity);
                return mapper.toDto(saved);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                System.err.println("Erro de integridade de dados ao criar evento: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Falha na criação do evento. Verifique se todos os campos obrigatórios foram preenchidos corretamente, incluindo 'tipoMusico' e o status inicial.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro desconhecido ao criar evento: " + e.getMessage());
            }
        }
    }

    //Métodos PUT
    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public EventoResponseDTO alterarEvento(EventoUpdateDTO dto, Long idEvento, String token){
        Long idContratante = JwtUtil.extrairUsuarioId(token);
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        Evento evento = repository.findById(idEvento).orElseThrow(() -> new EntityNotFoundException("Id de evento não encontrado."));
        if (evento.getContratante() == contratante){
            if (evento.getHoraInicio().isAfter(evento.getHoraFim())) {
                mapper.updateFromDto(dto, evento);
                Evento saved = repository.save(evento);
                return mapper.toDto(saved);
            }
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o evento precisa começar antes de terminar");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Somente o dono desse evento pode alterá-lo");
        }

    }

    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public EventoResponseDTO addMusico(EventoUpdateDTO dto, Long idMusico, Long idEvento, String token){
        Long idContratante = JwtUtil.extrairUsuarioId(token);
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        Evento evento = repository.findById(idEvento).orElseThrow(() -> new EntityNotFoundException("Id de evento não encontrado."));
        Musico musico = musicoRepository.findById(idMusico).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        if (evento.getContratante() == contratante){
            if (evento.getHoraInicio().isAfter(evento.getHoraFim())) {
                evento.setMusico(musico);
                mapper.updateFromDto(dto, evento);
                Evento saved = repository.save(evento);
                return mapper.toDto(saved);
            }
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o evento precisa começar antes de terminar");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Somente o dono desse evento pode alterá-lo");
        }

    }

    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public void adicionarMediaUrl(Long idEvento, MediaUrlRequestDTO dto){
        Evento evento = repository.findById(idEvento).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        evento.getMediaUrl().add(dto.mediaUrl());
        repository.save(evento);
    }

    //Métodos DELETE
    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public boolean excluirEvento(String token){
        Long id = JwtUtil.extrairUsuarioId(token);
        Optional<Evento> evento = repository.findById(id);
        if (evento.isPresent()){
            repository.deleteById(id);
            return true;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não pode excluir um evento inexistente!");
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CONTRATANTE')")
    public void DeleteMediaUrl(Long idEvento, MediaUrlRequestDTO dto){
        Evento evento = repository.findById(idEvento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));
        boolean removed = evento.getMediaUrl().remove(dto.mediaUrl());
        if (!removed){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url não encontrada");
        }
    }


}
