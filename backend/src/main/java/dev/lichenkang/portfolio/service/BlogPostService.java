package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.dto.BlogPostDto;
import dev.lichenkang.portfolio.entity.BlogPost;
import dev.lichenkang.portfolio.repository.BlogPostRepository;
import dev.lichenkang.portfolio.request.BlogPostRequest;
import dev.lichenkang.portfolio.util.MediaPathUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final ContentStorageProperties storageProperties;
    private final MarkdownService markdownService;

    public BlogPostService(BlogPostRepository blogPostRepository,
                           ContentStorageProperties storageProperties,
                           MarkdownService markdownService) {
        this.blogPostRepository = blogPostRepository;
        this.storageProperties = storageProperties;
        this.markdownService = markdownService;
    }

    @Transactional(readOnly = true)
    public List<BlogPostDto> getPublishedPosts() {
        return blogPostRepository.findAllByPublishedTrueAndPublishDateBeforeOrderByPublishDateDesc(Instant.now())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BlogPostDto getBySlug(String slug) {
        return blogPostRepository.findBySlug(slug)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + slug));
    }

    @Transactional
    public BlogPostDto create(BlogPostRequest request) {
        BlogPost post = new BlogPost();
        applyRequest(post, request);
        return toDto(blogPostRepository.save(post));
    }

    @Transactional
    public BlogPostDto update(Long id, BlogPostRequest request) {
        BlogPost post = blogPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + id));
        applyRequest(post, request);
        return toDto(blogPostRepository.save(post));
    }

    @Transactional(readOnly = true)
    public List<BlogPostDto> getAll() {
        return blogPostRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new EntityNotFoundException("Post not found: " + id);
        }
        blogPostRepository.deleteById(id);
    }

    private void applyRequest(BlogPost post, BlogPostRequest request) {
        post.setTitle(request.getTitle());
        post.setSlug(request.getSlug());
        post.setSummary(request.getSummary());
        post.setContent(request.getContent());
        post.setCoverImagePath(MediaPathUtils.normalize(request.getCoverImagePath(), storageProperties.getMediaBaseUrl()));
        post.setPublishDate(request.getPublishDate());
        post.setFeatured(request.isFeatured());
        post.setPublished(request.isPublished());
        post.setTags(request.getTags());
    }

    private BlogPostDto toDto(BlogPost post) {
        var renderResult = markdownService.render(post.getContent());
        return new BlogPostDto(
                post.getId(),
                post.getTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getContent(),
                renderResult.getHtml(),
                renderResult.getHeadings(),
                buildMediaUrl(post.getCoverImagePath()),
                post.getPublishDate(),
                post.isFeatured(),
                post.isPublished(),
                post.getTags(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    private String buildMediaUrl(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        return MediaPathUtils.buildPublicUrl(relativePath, storageProperties.getMediaBaseUrl());
    }
}
