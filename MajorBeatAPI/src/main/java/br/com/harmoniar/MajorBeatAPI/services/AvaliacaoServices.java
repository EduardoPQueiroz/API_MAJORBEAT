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


    public List<AvaliacaoResponseDTO> getAvaliacoesById(Long id){
        Optional<Musico> musicoOptional = musicoRepository.findById(id);
        if (musicoOptional.isPresent()){
            List<Avaliacao> avaliacoes = musicoOptional.get().getAvaliacoes();
            return mapper.toResponseDTOList(avaliacoes);
        }else{
            Optional<Contratante> contratanteOptional = contratanteRepository.findById(id);
            if (contratanteOptional.isPresent()){
                List<Avaliacao> avaliacoes = contratanteOptional.get().getAvaliacoes();
                return mapper.toResponseDTOList(avaliacoes);
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um usuário com o id informado");
            }
        }
    }

    public Double getMediaAvaliacaoByIdUsuario(Long id){
            Optional<Musico> musicoOpt = musicoRepository.findById(id);
            if (musicoOpt.isPresent()){
                List<Avaliacao> avaliacoes = musicoOpt.get().getAvaliacoes();
                if (avaliacoes.isEmpty() || avaliacoes.equals(null)){
                    return 0.0;
                }
                double soma = 0;
                for (Avaliacao a: avaliacoes){
                    soma += a.getNota();
                }
                return soma / avaliacoes.size();
            }
            else{
                Optional<Contratante> contratanteOpt = contratanteRepository.findById(id);
                if (contratanteOpt.isPresent()){
                    List<Avaliacao> avaliacoes = musicoOpt.get().getAvaliacoes();
                    if (avaliacoes.isEmpty() || avaliacoes.equals(null)){
                        return 0.0;
                    }
                    double soma = 0;
                    for (Avaliacao a: avaliacoes){
                        soma += a.getNota();
                    }
                    return soma / avaliacoes.size();
                }
                else{
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um usuário com o id informado");
                }
            }

    }

}
