package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ContratanteResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import br.com.harmoniar.MajorBeatAPI.mappers.ContratanteMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
        Optional<Contratante> contratante = repository.getByNome(nomeContratante);
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
    public ContratanteResponseDTO cadastrarContratante(ContratanteResponseDTO dto){

        Contratante entity = mapper.toEntity(dto);
        if (!entity.getTelefone().matches("\\d{10,11}")){
            throw new IllegalArgumentException("Número de telefone inválido inserido");
        }
        if(!entity.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Email inválido inserido!");
        }
        Contratante saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public String autenticarContratante(String nome, String senhaDigitada){
        Optional<Contratante> contratante = repository.getByNome(nome);
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

    //Métodos PUT
    public ContratanteResponseDTO editContratanteById(ContratanteResponseDTO dto, Long id){
        Optional<Contratante> existe = repository.findById(id);
        Contratante contratante = mapper.toEntity(dto);
        if (existe.isPresent()){
            if (!contratante.getTelefone().matches("\\d{10,11}")){
                throw new IllegalArgumentException("Número de telefone inválido");
            }
            if(!contratante.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
                throw new IllegalArgumentException("Endereço de email inválido inserido!");
            }
            else{
                Contratante saved = repository.save(contratante);
                return mapper.toDto(saved);
            }
        }
        else{
            throw new NullPointerException("Não é possível alterar um contratante inexistente");
        }
    }

}
