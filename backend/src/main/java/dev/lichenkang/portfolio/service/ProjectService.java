package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.dto.ProjectDto;
import dev.lichenkang.portfolio.entity.Project;
import dev.lichenkang.portfolio.repository.ProjectRepository;
import dev.lichenkang.portfolio.request.ProjectRequest;
import dev.lichenkang.portfolio.util.MediaPathUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ContentStorageProperties storageProperties;

    public ProjectService(ProjectRepository projectRepository, ContentStorageProperties storageProperties) {
        this.projectRepository = projectRepository;
        this.storageProperties = storageProperties;
    }

    @Transactional(readOnly = true)
    public List<ProjectDto> getPublishedProjects() {
        return projectRepository.findAllByPublishedTrueOrderBySortOrderAscTitleAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDto create(ProjectRequest request) {
        Project project = new Project();
        applyRequest(project, request);
        Project saved = projectRepository.save(project);
        return toDto(saved);
    }

    @Transactional
    public ProjectDto update(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));
        applyRequest(project, request);
        return toDto(projectRepository.save(project));
    }

    @Transactional(readOnly = true)
    public ProjectDto get(Long id) {
        return projectRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project not found: " + id);
        }
        projectRepository.deleteById(id);
    }

    private void applyRequest(Project project, ProjectRequest request) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setLink(request.getLink());
        project.setCoverImagePath(MediaPathUtils.normalize(request.getCoverImagePath(), storageProperties.getMediaBaseUrl()));
        project.setVideoPath(MediaPathUtils.normalize(request.getVideoPath(), storageProperties.getMediaBaseUrl()));
        project.setTags(request.getTags());
        project.setFeatured(request.isFeatured());
        project.setPublished(request.isPublished());
        project.setSortOrder(request.getSortOrder());
    }

    private ProjectDto toDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getLink(),
                buildMediaUrl(project.getCoverImagePath()),
                buildMediaUrl(project.getVideoPath()),
                project.getTags(),
                project.isFeatured(),
                project.isPublished(),
                project.getSortOrder(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    private String buildMediaUrl(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        return MediaPathUtils.buildPublicUrl(relativePath, storageProperties.getMediaBaseUrl());
    }
}
