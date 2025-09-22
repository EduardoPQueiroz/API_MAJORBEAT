package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Chat;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByMusico(Musico musico);

    List<Chat> findAllByContratante(Contratante contratante);

    @Query("SELECT c FROM Chat c WHERE c.musico = :musico AND c.contratante = :contratante")
    Optional<Chat> findByMusicoAndContratante(@Param("musico") Musico musico,
                                              @Param("contratante") Contratante contratante);
}
