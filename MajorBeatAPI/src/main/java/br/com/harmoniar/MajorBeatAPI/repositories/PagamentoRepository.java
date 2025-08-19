package br.com.harmoniar.MajorBeatAPI.repositories;

import br.com.harmoniar.MajorBeatAPI.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
