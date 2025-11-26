package dev.lichenkang.portfolio.dto;

import java.time.Instant;

public class MediaAssetDto {
    private final Long id;
    private final String originalFilename;
    private final String relativePath;
    private final String publicUrl;
    private final String mimeType;
    private final long sizeInBytes;
    private final String category;
    private final Instant createdAt;

    public MediaAssetDto(Long id,
                         String originalFilename,
                         String relativePath,
                         String publicUrl,
                         String mimeType,
                         long sizeInBytes,
                         String category,
                         Instant createdAt) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.relativePath = relativePath;
        this.publicUrl = publicUrl;
        this.mimeType = mimeType;
        this.sizeInBytes = sizeInBytes;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getCategory() {
        return category;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
