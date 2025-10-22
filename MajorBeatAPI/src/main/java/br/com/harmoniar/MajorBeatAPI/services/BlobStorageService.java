package br.com.harmoniar.MajorBeatAPI.services;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class BlobStorageService {

    private final BlobContainerClient containerClient;

    public BlobStorageService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName) {

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

        return blobClient.getBlobUrl();
    }
}
