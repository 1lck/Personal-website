package dev.lichenkang.portfolio.dto;

import java.time.Instant;
import java.util.List;

public class BlogPostDto {
    private final Long id;
    private final String title;
    private final String slug;
    private final String summary;
    private final String content;
    private final String contentHtml;
    private final List<HeadingDto> headings;
    private final String coverImageUrl;
    private final Instant publishDate;
    private final boolean featured;
    private final boolean published;
    private final List<String> tags;
    private final Instant createdAt;
    private final Instant updatedAt;

    public BlogPostDto(Long id,
                       String title,
                       String slug,
                       String summary,
                       String content,
                       String contentHtml,
                       List<HeadingDto> headings,
                       String coverImageUrl,
                       Instant publishDate,
                       boolean featured,
                       boolean published,
                       List<String> tags,
                       Instant createdAt,
                       Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.content = content;
        this.contentHtml = contentHtml;
        this.headings = headings;
        this.coverImageUrl = coverImageUrl;
        this.publishDate = publishDate;
        this.featured = featured;
        this.published = published;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public List<HeadingDto> getHeadings() {
        return headings;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public boolean isFeatured() {
        return featured;
    }

    public boolean isPublished() {
        return published;
    }

    public List<String> getTags() {
        return tags;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
