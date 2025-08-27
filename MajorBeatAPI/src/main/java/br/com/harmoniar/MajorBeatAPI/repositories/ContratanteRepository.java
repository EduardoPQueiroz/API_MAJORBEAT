package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
    List<Contratante> getByTipoContratante(TipoContratante tipoContratante);

    Optional<Contratante> getByNome(String nomeContratante);


}
