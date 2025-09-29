package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.MusicoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MusicoServices {

    @Autowired
    private MusicoRepository repository;

    @Autowired
    private MusicoMapper mapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Get
    public List<MusicoResponseDTO> getAllMusicos(){

        try{
            return mapper.toResponseDTOList(repository.findAll());
        }catch(ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public MusicoResponseDTO getMusicoById(Long id) {
        Musico musico = repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        return mapper.toDto(musico);
    }

    public MusicoResponseDTO getMusicoByNome(String nome){
        Musico musico = repository.findByNome(nome).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        return mapper.toDto(musico);
    }

    public MusicoResponseDTO getMusicoByEmail(String email){
        if (email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            Musico musico = repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado!"));
                return mapper.toDto(musico);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido inserido");
        }
    }

    public List<MusicoResponseDTO> getMusicoByTipoMusico(TipoMusico tipoMusico){
        List<Musico> musico = repository.findByTipoMusico(tipoMusico);
        if (musico.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum músico foi encontrado!");
        }
        return mapper.toResponseDTOList(musico);
    }

    //Post
    public MusicoResponseDTO cadastrarMusico(MusicoRequestDTO dto) {
        Musico entity = mapper.toEntity(dto);

        if (!entity.getTelefone().matches("\\d{10,11}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido inserido");
        }
        if (!entity.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido inserido");
        }
        entity.setSenha(passwordEncoder.encode(dto.senha()));
        entity.setDtCriacao(LocalDate.now());
        entity.setRole(Role.ROLE_MUSICO);
        Musico saved = repository.save(entity);
        return mapper.toDto(saved);

    }
    //Autenticar Musico...
    public String autenticarMusico(String nome, String email, String senhaDigitada) {
        Musico musico;

        if (nome != null && !nome.isEmpty()) {
            musico = repository.findByNome(nome)
                    .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado um músico com esse nome"));
        } else if (email != null && !email.isEmpty()) {
            musico = repository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado um músico com esse email"));
        } else {
            throw new NullPointerException("Insira um nome ou email para autenticar o usuário");
        }

        if (!passwordEncoder.matches(senhaDigitada, musico.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }

        return JwtUtil.gerarToken(musico.getIdMusico(), Role.ROLE_MUSICO);
    }


    //Put
    public MusicoResponseDTO editMusicoById(MusicoUpdateDTO dto, String token) {
        Long id = JwtUtil.extrairUsuarioId(token);
        Musico musico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não é possível editar um músico que ainda não existe"));

        if (dto.telefone() != null && !dto.telefone().matches("\\d{10,11}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido inserido!");
        }

        mapper.updateFromDto(dto, musico);

        Musico saved = repository.save(musico);
        return mapper.toDto(saved);
    }

    //Patch
    public void adicionarMediaUrl(String token, MediaUrlRequestDTO dto){
        Long idMusico = JwtUtil.extrairUsuarioId(token);
        Musico musico = repository.findById(idMusico).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Músico nao encontrado"));
        musico.getMediaUrl().add(dto.mediaUrl());
        repository.save(musico);
    }

    //Delete
    public boolean deleteMusicoById(String token) {
        Long id = JwtUtil.extrairUsuarioId(token);
        Optional<Musico> musico = repository.findById(id);
        if (musico.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteMediaUrl(MediaUrlRequestDTO dto, String token){
        Long idMusico = JwtUtil.extrairUsuarioId(token);
        Musico musico = repository.findById(idMusico).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico nao encontrado"));
        boolean removed = musico.getMediaUrl().remove(dto.mediaUrl());
        if (!removed){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url não encontrada");
        }
    }


}

