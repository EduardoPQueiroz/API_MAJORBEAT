package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, Long> {
}
