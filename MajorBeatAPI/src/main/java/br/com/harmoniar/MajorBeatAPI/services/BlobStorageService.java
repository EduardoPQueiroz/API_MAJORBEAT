package br.com.harmoniar.MajorBeatAPI.services;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlobStorageService {

    private final BlobContainerClient containerClient;

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.account-key}")
    private String accountKey;

    public BlobStorageService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName,
            @Value("${azure.storage.account-name}") String accountName,
            @Value("${azure.storage.account-key}") String accountKey) {

        this.accountName = accountName;
        this.accountKey = accountKey;

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        this.containerClient = serviceClient.getBlobContainerClient(containerName);

        if (!containerClient.exists()) {
            containerClient.create();
        }
    }

    public String uploadFile(InputStream inputStream, long length, String contentType, String userId) {
        String blobName = "musicos/" + userId + "/" + UUID.randomUUID();
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        blobClient.upload(inputStream, length, true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));

        // Permissão de leitura
        BlobSasPermission permission = new BlobSasPermission().setReadPermission(true);

        // Cria valores do SAS (expira em 100h)
        OffsetDateTime expiryTime = OffsetDateTime.now().plusHours(100);
        BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, permission)
                .setStartTime(OffsetDateTime.now());

        // ⚠️ Corrigir geração do SAS: use o blobClient com a credencial
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
        BlobClient clientComCredencial = new BlobClientBuilder()
                .endpoint(blobClient.getBlobUrl())
                .credential(credential)
                .buildClient();

        // Gera o SAS corretamente vinculado ao blob
        String sasToken = clientComCredencial.generateSas(sasValues);

        return blobClient.getBlobUrl() + "?" + sasToken;
    }


    public List<String> uploadMultipleFiles(List<MultipartFile> files, String userId) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try (InputStream inputStream = file.getInputStream()) {
                String url = uploadFile(
                        inputStream,
                        file.getSize(),
                        file.getContentType(),
                        userId
                );
                urls.add(url);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao enviar arquivo: " + file.getOriginalFilename(), e);
            }
        }

        return urls;
    }

    public List<String> uploadMultipleTempFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try (InputStream inputStream = file.getInputStream()) {
                // Pasta temporária "temp"
                String url = uploadFile(
                        inputStream,
                        file.getSize(),
                        file.getContentType(),
                        "temp" // usar "temp" como diretório
                );
                urls.add(url);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao enviar arquivo: " + file.getOriginalFilename(), e);
            }
        }

        return urls;
    }


}
