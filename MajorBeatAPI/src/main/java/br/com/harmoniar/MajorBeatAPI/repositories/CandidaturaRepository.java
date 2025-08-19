package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}
