package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.ChatRequestDTO;
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

import java.time.LocalDateTime;
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

    public ChatResponseDTO criarChat(ChatRequestDTO dto){
        Long idMusico = dto.musico().getIdMusico();
        Long idContratante = dto.contratante().getIdContratante();

        Musico musico = musicoRepository.findById(idMusico).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musico não encontrado"));
        Contratante contratante = contratanteRepository.findById(idContratante).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contratante não encontrado"));

        Optional<Chat> existingChat = repository.findByMusicoAndContratante(musico, contratante);
        if (existingChat.isPresent()){
            return mapper.toResponseDto(existingChat.get());
        }
        Chat chat = new Chat();
        chat.setContratante(contratante);
        chat.setMusico(musico);
        chat.setDataInicio(LocalDateTime.now());

        Chat chatSaved = repository.save(chat);
        return mapper.toResponseDto(chatSaved);
    }

    public void deleteChatById(Long id){
        Optional<Chat> chatOptional = repository.findById(id);
        if(chatOptional.isPresent()){
            repository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível excluir o chat pois ele não existe");
        }
    }


}
