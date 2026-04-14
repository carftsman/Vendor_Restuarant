package com.dhatvibs.modules.config;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.PublicAccessType;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Slf4j
public class AzureBlobService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private BlobContainerClient containerClient;

    @PostConstruct
    public void init() {
        try {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();

            containerClient = blobServiceClient.getBlobContainerClient(containerName);

            // ✅ Create container if not exists
            if (!containerClient.exists()) {
                containerClient.create();

                // 🔥 IMPORTANT: Make container PUBLIC
                containerClient.setAccessPolicy(PublicAccessType.BLOB, null);

                log.info("✅ Container created & set to PUBLIC");
            } else {
                log.info("✅ Using existing container: {}", containerName);
            }

        } catch (Exception e) {
            log.error("❌ Azure initialization failed", e);
            throw new RuntimeException(e);
        }
    }

    public String uploadFile(MultipartFile file) {
        try {
            // ✅ Clean filename
            String originalName = file.getOriginalFilename().replaceAll("\\s+", "_");

            String fileName = UUID.randomUUID() + "_" + originalName;

            BlobClient blobClient = containerClient.getBlobClient(fileName);

            // ✅ Upload
            blobClient.upload(file.getInputStream(), file.getSize(), true);

            String url = blobClient.getBlobUrl();

            log.info("✅ File uploaded to Azure: {}", url);

            return url;

        } catch (Exception e) {
            log.error("❌ Azure upload failed", e);
            throw new RuntimeException("Azure upload failed: " + e.getMessage());
        }
    }
}