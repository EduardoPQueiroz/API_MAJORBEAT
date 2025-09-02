package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicoRepository extends JpaRepository<Musico, Long> {
    List<Musico> getByTipoMusico(TipoMusico tipoMusico);

    Optional<Musico> getByNome(String nome);

    Optional<Musico> getByEmail(String email);
}
