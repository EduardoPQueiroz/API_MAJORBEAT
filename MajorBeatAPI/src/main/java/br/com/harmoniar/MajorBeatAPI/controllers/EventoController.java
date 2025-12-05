package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.EventoRequestDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoResponseDTO;
import br.com.harmoniar.MajorBeatAPI.dto.EventoUpdateDTO;
import br.com.harmoniar.MajorBeatAPI.dto.MediaUrlRequestDTO;
import br.com.harmoniar.MajorBeatAPI.enums.NomeGenero;
import br.com.harmoniar.MajorBeatAPI.enums.NomeInstrumento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoEvento;
import br.com.harmoniar.MajorBeatAPI.enums.TipoMusico;
import br.com.harmoniar.MajorBeatAPI.mappers.EventoMapper;
import br.com.harmoniar.MajorBeatAPI.services.BlobStorageService;
import br.com.harmoniar.MajorBeatAPI.services.EventoServices;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Eventos")
public class EventoController {
    @Autowired
    EventoServices services;
    @Autowired
    BlobStorageService blobStorageService;
    @Autowired
    EventoMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos(){
            return ResponseEntity.ok(services.getAllEventos());
    }

    @GetMapping("/getByTipoMusico/{tipoMusico}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByTipoMusico(@PathVariable TipoMusico tipoMusico){
            return ResponseEntity.ok(services.getEventosByTipoMusico(tipoMusico));
    }

    @GetMapping("/getByTipoEvento/{tipoEvento}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByTipoEvento(@PathVariable TipoEvento tipoEvento){
        return ResponseEntity.ok(services.getEventosByTipoEvento(tipoEvento));
    }

    @GetMapping("/getByData/{data}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByData(@PathVariable LocalDate data){
            return ResponseEntity.ok(services.getEventosByData(data));
    }

    @GetMapping("/getByEndereco/{endereco}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByEndereco(@PathVariable String endereco){
            return ResponseEntity.ok(services.getEventosByEndereco(endereco));
    }

    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<EventoResponseDTO> getEventoByNome(@PathVariable String nome){
            return ResponseEntity.ok(services.getEventoByNome(nome));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id){
            return ResponseEntity.ok(services.getEventoById(id));
    }

    @GetMapping("/getByGenero/{nomeGenero}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByGenero(@PathVariable NomeGenero nomeGenero){
        return ResponseEntity.ok(services.getEventosByGenero(nomeGenero));
    }

    @GetMapping("/getByInstrumento/{instrumento}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByInstrumento(@PathVariable NomeInstrumento instrumento){
        return ResponseEntity.ok(services.getEventosByInstrumento(instrumento));
    }

    @GetMapping("/getByIdContratante/{idContratante}")
    public ResponseEntity<List<EventoResponseDTO>> getEventoByIdContratante(@PathVariable Long idContratante){
        return ResponseEntity.ok(services.getEventosByContratanteId(idContratante));
    }

    //Métodos POST

    @PostMapping("/criar")
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer ", "").trim();
            return ResponseEntity.ok(services.criarEvento(dto, token));
    }

    @PostMapping("/uploadMediaEvento")
    public ResponseEntity<Map<String, String>> uploadMediaEvento(
            Long idEvento,
            @RequestParam("file") MultipartFile file) {

        String folderName = "evento";

        try {
            // Faz upload no Azure
            String url = blobStorageService.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType(),
                    idEvento.toString(),
                    folderName
            );

            // Adiciona URL ao evento no banco (crie método equivalente no service)
            services.adicionarMediaUrl(idEvento, new MediaUrlRequestDTO(url));

            // Retorna a URL para o app
            return ResponseEntity.ok(Map.of("url", url));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar arquivo"));
        }
    }


    @PostMapping("/uploadTempEvento")
    public ResponseEntity<Map<String, String>> uploadTempMediaEvento(
            @RequestParam("file") MultipartFile file) {

        String folderName = "evento";

        try {
            String url = blobStorageService.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType(),
                    "temp",
                    folderName
            );

            return ResponseEntity.ok(Map.of("url", url));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar arquivo"));
        }
    }


    @PostMapping("/uploadMultiEvento")
    public ResponseEntity<?> uploadMultiplasMidiasEvento(
            Long idEvento,
            @RequestParam("files") List<MultipartFile> files) {
        String folderName = "evento";

        try {
            List<String> urls = blobStorageService.uploadMultipleFiles(files, idEvento.toString(), folderName);

            // Salva todas as URLs no banco (crie método equivalente)
            for (String url : urls) {
                services.adicionarMediaUrl(idEvento, new MediaUrlRequestDTO(url));
            }

            return ResponseEntity.ok(Map.of("urls", urls));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar múltiplos arquivos"));
        }
    }


    @PostMapping("/uploadTempMultiEvento")
    public ResponseEntity<Map<String, List<String>>> uploadTempMultiplasMidiasEvento(
            @RequestParam("files") List<MultipartFile> files) {

        String folderName = "evento";

        try {
            List<String> urls = blobStorageService.uploadMultipleFilesInFolder(files, folderName);

            return ResponseEntity.ok(Map.of("urls", urls));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", List.of("Falha ao enviar múltiplos arquivos")));
        }
    }


    //Métodos PUT
    @PutMapping("/atualizar")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@RequestBody EventoUpdateDTO dto, @PathVariable Long idEvento, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer", "");
            return ResponseEntity.ok(services.alterarEvento(dto, idEvento, token));
    }

    @PutMapping("/addMusico/{idEvento}/{idMusico}")
    public ResponseEntity<EventoResponseDTO> addMusico(@RequestBody EventoUpdateDTO dto, @PathVariable Long idEvento, @PathVariable Long idMusico, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        return ResponseEntity.ok(services.addMusico(dto, idMusico, idEvento, token));
    }

    //PATCH
    @PatchMapping("/addMedia")
    public ResponseEntity<Void> adicionarMediaUrl(Long idEvento, @RequestBody MediaUrlRequestDTO dto){
        services.adicionarMediaUrl(idEvento, dto);
        return ResponseEntity.ok().build();
    }

    //Métodos DELETE

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEventoById(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "");
        if (services.excluirEvento(token) == true){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/deleteMedia")
    public ResponseEntity<Void> deleteMedia(Long idEvento, @RequestBody MediaUrlRequestDTO dto){
        services.DeleteMediaUrl(idEvento, dto);
        return ResponseEntity.ok().build();
    }




}
