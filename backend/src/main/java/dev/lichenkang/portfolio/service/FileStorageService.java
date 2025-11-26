package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.service.model.StoredFile;
import dev.lichenkang.portfolio.util.MediaPathUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path storageLocation;
    private final String mediaBaseUrl;

    public FileStorageService(ContentStorageProperties properties) {
        this.storageLocation = Paths.get(properties.getStorageRoot()).toAbsolutePath().normalize();
        this.mediaBaseUrl = properties.getMediaBaseUrl();

        try {
            Files.createDirectories(this.storageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    public StoredFile store(MultipartFile file, String subfolder) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        
        try {
            if (originalFilename.contains("..")) {
                throw new RuntimeException("Invalid file path: " + originalFilename);
            }

            String extension = "";
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = originalFilename.substring(dotIndex);
            }

            String uniqueFilename = UUID.randomUUID().toString() + extension;
            Path subfolderPath = this.storageLocation.resolve(subfolder);
            Files.createDirectories(subfolderPath);

            Path targetLocation = subfolderPath.resolve(uniqueFilename);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            String relativePath = subfolder + "/" + uniqueFilename;
            String accessUrl = this.mediaBaseUrl + "/" + relativePath;

            return new StoredFile(
                originalFilename,
                uniqueFilename,
                relativePath,
                accessUrl,
                file.getContentType(),
                file.getSize(),
                Instant.now()
            );

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + originalFilename, e);
        }
    }

    public Path loadFile(String relativePath) {
        return storageLocation.resolve(relativePath).normalize();
    }

    public Resource loadAsResource(String relativePath) {
        Path filePath = loadFile(relativePath);
        return new FileSystemResource(filePath.toFile());
    }

    public boolean deleteFile(String relativePath) {
        try {
            Path filePath = loadFile(relativePath);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            return false;
        }
    }

    public String buildPublicUrl(String relativePath) {
        return MediaPathUtils.buildPublicUrl(relativePath, this.mediaBaseUrl);
    }
}
