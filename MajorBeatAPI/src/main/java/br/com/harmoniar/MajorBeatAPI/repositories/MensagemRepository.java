package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Mensagem;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<Mensagem> findAllByIdMusico_IdMusico(Long idMusico);

    List<Mensagem> findAllByIdContratante_IdContratante(Long idContratante);

}
