package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.mappers.ChatMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.ChatRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServices {

    @Autowired
    private ChatRepository repository;

    @Autowired
    private MusicoRepository musicoRepository;

    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private ChatMapper mapper;

    public List<ChatResponseDTO> listarChatsUsuarioAutenticado(String token){
        Long idUsuario = JwtUtil.extrairUsuarioId(token);
        if (!idUsuario.equals(null)){
            Optional<Musico> musicoOptional = musicoRepository.findById(idUsuario);
            if (musicoOptional.isPresent()){
                List<Chat> chats = musicoOptional.get().getChats();
                return mapper.toResponseDTOList(chats);
            }else{
                Optional<Contratante> contratanteOptional = contratanteRepository.findById(idUsuario);
                if (contratanteOptional.isPresent()){
                    List<Chat> chats = contratanteOptional.get().getChats();
                    return mapper.toResponseDTOList(chats);
                }else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um usuário com o id informado");
                }
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado");
        }
    }


}
