package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicoRepository extends JpaRepository<Musico, Long> {
    List<Musico> findByTipoMusico(TipoMusico tipoMusico);

    List<Musico> findByNomeGenerosContaining(NomeGenero genero);

    List<Musico> findByNomeInstrumentosContaining(NomeInstrumento instrumento);

    Optional<Musico> findByNome(String nome);

    Optional<Musico> findByEmail(String email);
}
