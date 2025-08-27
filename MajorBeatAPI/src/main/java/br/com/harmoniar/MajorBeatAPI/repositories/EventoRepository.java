package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Evento;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findEventoByTipoMusico(TipoMusico tipoMusico);
    List<Evento> findByInstrumentosContaining(NomeInstrumento instrumento);

    List<Evento> findByGeneroContaining(NomeGenero genero);
    List<Evento> findByData(LocalDate data);
    List<Evento> findByEndereco(String endereco);
    Optional<Evento> findByNome(String nome);

}
