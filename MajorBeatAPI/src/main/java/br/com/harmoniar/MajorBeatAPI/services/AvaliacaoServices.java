package br.com.harmoniar.MajorBeatAPI.services;

import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.AvaliacaoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.entity.Avaliacao;
import br.com.harmoniar.MajorBeatAPI.entity.Contratante;
import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.mappers.AvaliacaoMapper;
import br.com.harmoniar.MajorBeatAPI.repositories.AvaliacaoRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.ContratanteRepository;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoServices {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private AvaliacaoMapper mapper;
    @Autowired
    private MusicoRepository musicoRepository;
    @Autowired
    private ContratanteRepository contratanteRepository;


        public AvaliacaoResponseDTO avaliar(AvaliacaoRequestDTO dto, String token) {

            Long idAvaliador = JwtUtil.extrairUsuarioId(token);

            Avaliacao avaliacao = mapper.toEntity(dto);

            Optional<Musico> musicoOpt = musicoRepository.findById(idAvaliador);
            if (musicoOpt.isPresent()) {
                Musico musico = musicoOpt.get();
                Contratante contratante = contratanteRepository.findById(dto.idRecebedor())
                        .orElseThrow(() -> new RuntimeException("Contratante não encontrado"));

                avaliacao.setIdAvaliador(musico.getIdMusico());
                avaliacao.setIdRecebedor(contratante.getIdContratante());

                Avaliacao saved = repository.save(avaliacao);
                return mapper.toDto(saved);
            }

            Optional<Contratante> contratanteOpt = contratanteRepository.findById(idAvaliador);
            if (contratanteOpt.isPresent()) {
                Contratante contratante = contratanteOpt.get();
                Musico musico = musicoRepository.findById(dto.idRecebedor())
                        .orElseThrow(() -> new RuntimeException("Músico não encontrado"));

                avaliacao.setIdAvaliador(contratante.getIdContratante());
                avaliacao.setIdRecebedor(musico.getIdMusico());

                Avaliacao saved = repository.save(avaliacao);
                return mapper.toDto(saved);
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para este token");
    }


    public List<AvaliacaoResponseDTO> listarAvaliacoes(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public AvaliacaoResponseDTO getAvaliacaoById(Long id){
        Optional<Avaliacao> entity = repository.findById(id);
        return mapper.toDto(entity.orElse(null));
    }
}
