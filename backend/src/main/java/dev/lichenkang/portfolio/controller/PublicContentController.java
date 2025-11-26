package dev.lichenkang.portfolio.controller;

import dev.lichenkang.portfolio.dto.BlogPostDto;
import dev.lichenkang.portfolio.dto.ProfileDto;
import dev.lichenkang.portfolio.dto.ProjectDto;
import dev.lichenkang.portfolio.dto.WorkDto;
import dev.lichenkang.portfolio.service.BlogPostService;
import dev.lichenkang.portfolio.service.ProfileService;
import dev.lichenkang.portfolio.service.ProjectService;
import dev.lichenkang.portfolio.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicContentController {

    private final ProjectService projectService;
    private final BlogPostService blogPostService;
    private final ProfileService profileService;
    private final WorkService workService;

    public PublicContentController(ProjectService projectService, BlogPostService blogPostService, ProfileService profileService, WorkService workService) {
        this.projectService = projectService;
        this.blogPostService = blogPostService;
        this.profileService = profileService;
        this.workService = workService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects() {
        return ResponseEntity.ok(projectService.getPublishedProjects());
    }

    @GetMapping("/blog-posts")
    public ResponseEntity<List<BlogPostDto>> getBlogPosts() {
        return ResponseEntity.ok(blogPostService.getPublishedPosts());
    }

    @GetMapping("/blog-posts/{slug}")
    public ResponseEntity<BlogPostDto> getBlogPost(@PathVariable String slug) {
        return ResponseEntity.ok(blogPostService.getBySlug(slug));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile() {
        return ResponseEntity.ok(profileService.getProfile());
    }

    @GetMapping("/works")
    public ResponseEntity<List<WorkDto>> getWorks() {
        return ResponseEntity.ok(workService.getVisibleWorks());
    }
}
