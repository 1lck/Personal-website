package dev.lichenkang.portfolio.dto;

import java.time.Instant;
import java.util.List;

public class ProjectDto {
    private final Long id;
    private final String title;
    private final String description;
    private final String link;
    private final String coverImageUrl;
    private final String videoUrl;
    private final List<String> tags;
    private final boolean featured;
    private final boolean published;
    private final Integer sortOrder;
    private final Instant createdAt;
    private final Instant updatedAt;

    public ProjectDto(Long id,
                      String title,
                      String description,
                      String link,
                      String coverImageUrl,
                      String videoUrl,
                      List<String> tags,
                      boolean featured,
                      boolean published,
                      Integer sortOrder,
                      Instant createdAt,
                      Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.coverImageUrl = coverImageUrl;
        this.videoUrl = videoUrl;
        this.tags = tags;
        this.featured = featured;
        this.published = published;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isPublished() {
        return published;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
