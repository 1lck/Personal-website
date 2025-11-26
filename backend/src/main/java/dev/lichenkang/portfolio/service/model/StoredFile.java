package dev.lichenkang.portfolio.service.model;

import java.time.Instant;

public class StoredFile {
    private final String originalFilename;
    private final String storedFileName;
    private final String relativePath;
    private final String publicUrl;
    private final String contentType;
    private final long size;
    private final Instant uploadedAt;

    public StoredFile(String originalFilename,
                      String storedFileName,
                      String relativePath,
                      String publicUrl,
                      String contentType,
                      long size,
                      Instant uploadedAt) {
        this.originalFilename = originalFilename;
        this.storedFileName = storedFileName;
        this.relativePath = relativePath;
        this.publicUrl = publicUrl;
        this.contentType = contentType;
        this.size = size;
        this.uploadedAt = uploadedAt;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }
}
