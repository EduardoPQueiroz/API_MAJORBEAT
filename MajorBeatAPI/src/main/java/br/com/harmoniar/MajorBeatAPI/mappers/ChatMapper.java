package br.com.harmoniar.MajorBeatAPI.mappers;

import br.com.harmoniar.MajorBeatAPI.dto.ChatResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.repositories.ChatRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);
    List<ChatResponseDTO> toResponseDTOChatList(List<Chat> chats);
    List<ChatResponseDTO> toResponseDTOList(List<ChatRepository> chats);

}
