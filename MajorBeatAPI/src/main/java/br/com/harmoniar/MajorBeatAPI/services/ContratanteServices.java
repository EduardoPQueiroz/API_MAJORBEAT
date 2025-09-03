package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.ContratanteResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.ContratanteUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import br.com.harmoniar.MajorBeatAPI.mappers.ContratanteMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContratanteServices {

    @Autowired
    ContratanteRepository repository;

    @Autowired
    ContratanteMapper mapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    //Métodos GET
    public List<ContratanteResponseDTO> getAllContratantes(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public ContratanteResponseDTO getContratanteById(Long id){
        Optional<Contratante> contratante = repository.findById(id);
        if (contratante.isPresent()){
            return mapper.OptionalToDto(contratante);
        }
        throw new NullPointerException("Não existe um contratante com esse Id");
    }

    public ContratanteResponseDTO getContratanteByNome(String nomeContratante){
        Optional<Contratante> contratante = repository.getByNomeContratante(nomeContratante);
        if (contratante.isPresent()){
            return mapper.OptionalToDto(contratante);
        }
        throw new NullPointerException("Não existe um contratante com esse nome");
    }

    public List<ContratanteResponseDTO> getContratanteByTipoContratante(TipoContratante tipoContratante){
        List<Contratante> contratante = repository.getByTipoContratante(tipoContratante);
        if (contratante.isEmpty()){
            throw new NullPointerException("Nenhum contratante deste tipo foi encontrado");
        }
        return mapper.toResponseDTOList(contratante);
    }

    //Métodos DELETE
    public boolean DeleteContratanteById(Long id){
        Optional<Contratante> contratante = repository.findById(id);
        if (contratante.isPresent()){
            repository.deleteById(id);
            return true;
        }
        throw new NullPointerException("Não é possível deletar um contratante que não existe!");
    }

    //Métodos Post
    public ContratanteResponseDTO cadastrarContratante(ContratanteRequestDTO dto){

        Contratante entity = mapper.toEntity(dto);
        if (!entity.getTelefone().matches("\\d{10,11}")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido");
        }
        if(!entity.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email inválido inserido!");
        }
        entity.setSenha(passwordEncoder.encode(dto.senha()));
        entity.setRole(Role.CONTRATANTE);
        entity.setDtCriacao(LocalDate.now());
        Contratante saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public String autenticarContratante(String nome, String email, String senhaDigitada){
        if(nome.isEmpty()){
            if (email.isEmpty()){
                throw new NullPointerException("Insira o nome ou o email para realizar o login");
            }
            else{
                Optional<Contratante> contratante = repository.getByEmail(email);
                if(contratante.isPresent()){
                    if (passwordEncoder.matches(senhaDigitada, contratante.get().getSenha())){
                        return JwtUtil.gerarToken(nome);
                    }
                    else{
                        throw new RuntimeException("Senha incorreta inserida");
                    }
                }
                else{
                    throw new EntityNotFoundException("Não foi encontrado um contratante com esse email");
                }
            }
        }else {
            Optional<Contratante> contratante = repository.getByNomeContratante(nome);
            if (contratante.isPresent()){
                if (passwordEncoder.matches(senhaDigitada, contratante.get().getSenha())){
                    return JwtUtil.gerarToken(nome);
                }
                else{
                    throw new RuntimeException("Senha incorreta inserida");
                }
            }
            else{
                throw new RuntimeException("Usuário inexistente");
            }
        }
    }

    //Métodos PUT
    public ContratanteResponseDTO editContratanteById(ContratanteUpdateDTO dto, Long id){
        Contratante contratante = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado um contratante com esse id"));
            if (!contratante.getTelefone().matches("\\d{10,11}")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de telefone inválido inserido");
            }
            else{
                mapper.updateFromDto(dto, contratante);
                Contratante saved = repository.save(contratante);
                return mapper.toDto(saved);
            }
        }
        
}

