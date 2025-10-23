package br.com.harmoniar.MajorBeatAPI.controllers;

import br.com.harmoniar.MajorBeatAPI.dto.*;
import br.com.harmoniar.MajorBeatAPI.enums.TipoContratante;
import br.com.harmoniar.MajorBeatAPI.mappers.ContratanteMapper;
import br.com.harmoniar.MajorBeatAPI.services.BlobStorageService;
import br.com.harmoniar.MajorBeatAPI.services.ContratanteServices;
import br.com.harmoniar.MajorBeatAPI.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Contratante")
public class ContratanteController {
    @Autowired
    ContratanteServices services;
    @Autowired
    BlobStorageService blobStorageService;

    @Autowired
    ContratanteMapper mapper;

    //GET

    @GetMapping("/getAll")
    public ResponseEntity<List<ContratanteResponseDTO>> getAllContratantes(){
            return ResponseEntity.ok(services.getAllContratantes());
    }

    @GetMapping("/getByTipoContratante/{tipoContratante}")
    public ResponseEntity<List<ContratanteResponseDTO>> getContratanteByTipoContratante(@PathVariable TipoContratante tipoContratante){
            return ResponseEntity.ok(services.getContratanteByTipoContratante(tipoContratante));
    }
    @GetMapping("/getByNome/{nome}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteByNome(@PathVariable String nome) {
            return ResponseEntity.ok(services.getContratanteByNome(nome));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContratanteResponseDTO> getContratanteById(@PathVariable Long id){
            return ResponseEntity.ok(services.getContratanteById(id));
    }

    //POST
    @PostMapping("/cadastrar")
    public ResponseEntity<ContratanteResponseDTO> cadastrarContratante(@RequestBody ContratanteRequestDTO dto){
            return ResponseEntity.ok(services.cadastrarContratante(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> LoginContratante(@RequestBody LoginRequestDTO login){
        try{
            String token = services.autenticarContratante(login.nome(), login.email(), login.senha());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/uploadMedia")
    public ResponseEntity<Map<String, String>> uploadMediaContratante(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("file") MultipartFile file) {

        String token = authHeader.replace("Bearer", "").trim();
        Long idContratante = JwtUtil.extrairUsuarioId(token); // adapte se tiver método específico
        String folderName = "contratante";

        try {
            // Faz upload no Azure
            String url = blobStorageService.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType(),
                    idContratante.toString(),
                    folderName
            );

            // Adiciona URL ao contratante no banco
            services.adicionarMediaUrl(token, new MediaUrlRequestDTO(url)); // adapte para o seu service

            // Retorna a URL para o app
            return ResponseEntity.ok(Map.of("url", url));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar arquivo"));
        }
    }


    @PostMapping("/uploadTemp")
    public ResponseEntity<Map<String, String>> uploadTempMediaContratante(
            @RequestParam("file") MultipartFile file) {

        String folderName = "contratante";

        try {
            // Faz upload do arquivo no Azure e pega a URL SAS
            String url = blobStorageService.uploadFile(
                    file.getInputStream(),
                    file.getSize(),
                    file.getContentType(),
                    "temp",
                    folderName
            );

            // Retorna a URL apenas
            return ResponseEntity.ok(Map.of("url", url));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar arquivo"));
        }
    }


    @PostMapping("/uploadMulti")
    public ResponseEntity<?> uploadMultiplasMidiasContratante(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("files") List<MultipartFile> files) {

        String token = authHeader.replace("Bearer", "").trim();
        Long idContratante = JwtUtil.extrairUsuarioId(token);
        String folderName = "contratante";

        try {
            // Faz upload de todas as imagens
            List<String> urls = blobStorageService.uploadMultipleFiles(files, idContratante.toString(), folderName);

            // Salva todas as URLs no banco
            for (String url : urls) {
                services.adicionarMediaUrl(token, new MediaUrlRequestDTO(url));
            }

            // Retorna todas as URLs pro app
            return ResponseEntity.ok(Map.of("urls", urls));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Falha ao enviar múltiplos arquivos"));
        }
    }


    @PostMapping("/uploadTempMulti")
    public ResponseEntity<Map<String, List<String>>> uploadTempMultiplasMidiasContratante(
            @RequestParam("files") List<MultipartFile> files) {

        String folderName = "contratante";

        try {
            List<String> urls = blobStorageService.uploadMultipleFilesInFolder(files, folderName);

            return ResponseEntity.ok(Map.of("urls", urls));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", List.of("Falha ao enviar múltiplos arquivos")));
        }
    }



    //EDIT

    @PutMapping("/editContratanteById")
    public ResponseEntity<ContratanteResponseDTO> editContratanteById(ContratanteUpdateDTO dto, @RequestHeader("Authorization") String authHeader){
            String token = authHeader.replace("Bearer", "");
            return ResponseEntity.ok(services.editContratanteById(dto, token));
    }

    //PATCH
    @PatchMapping("/addMedia")
    public ResponseEntity<Void> adicionarMediaUrl(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.adicionarMediaUrl(token, dto);
        return ResponseEntity.ok().build();
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteContratanteById(@RequestHeader("Authorization" ) String authHeader){
        String token = authHeader.replace("Bearer", "");
        if(services.DeleteContratanteById(token) == true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteMedia")
    public ResponseEntity<Void> deleteMedia(@RequestHeader("Authorization") String authHeader, @RequestBody MediaUrlRequestDTO dto){
        String token = authHeader.replace("Bearer", "");
        services.DeleteMediaUrl(token, dto);
        return ResponseEntity.ok().build();
    }

}
