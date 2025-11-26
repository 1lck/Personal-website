package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.dto.WorkDto;
import dev.lichenkang.portfolio.entity.Work;
import dev.lichenkang.portfolio.repository.WorkRepository;
import dev.lichenkang.portfolio.request.WorkRequest;
import dev.lichenkang.portfolio.util.MediaPathUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final ContentStorageProperties storageProperties;

    public WorkService(WorkRepository workRepository, ContentStorageProperties storageProperties) {
        this.workRepository = workRepository;
        this.storageProperties = storageProperties;
    }

    @Transactional(readOnly = true)
    public List<WorkDto> getVisibleWorks() {
        return workRepository.findByIsShowTrueOrderBySortOrderAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WorkDto> getAll() {
        return workRepository.findAllByOrderBySortOrderAsc().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public WorkDto create(WorkRequest request) {
        Work work = new Work();
        applyRequest(work, request);
        Work saved = workRepository.save(work);
        return toDto(saved);
    }

    @Transactional
    public WorkDto update(Long id, WorkRequest request) {
        Work work = workRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Work not found: " + id));
        applyRequest(work, request);
        return toDto(workRepository.save(work));
    }

    @Transactional(readOnly = true)
    public WorkDto get(Long id) {
        return workRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Work not found: " + id));
    }

    @Transactional
    public void delete(Long id) {
        if (!workRepository.existsById(id)) {
            throw new EntityNotFoundException("Work not found: " + id);
        }
        workRepository.deleteById(id);
    }

    private void applyRequest(Work work, WorkRequest request) {
        work.setName(request.getName());
        work.setDescription(request.getDescription());
        work.setUrl(request.getUrl());
        work.setImagePath(MediaPathUtils.normalize(request.getImagePath(), storageProperties.getMediaBaseUrl()));
        work.setVideoPath(MediaPathUtils.normalize(request.getVideoPath(), storageProperties.getMediaBaseUrl()));
        work.setTags(request.getTags());
        work.setShow(request.isShow());
        work.setSortOrder(request.getSortOrder());
    }

    private WorkDto toDto(Work work) {
        return new WorkDto(
                work.getId(),
                work.getName(),
                work.getDescription(),
                work.getUrl(),
                buildMediaUrl(work.getImagePath()),
                buildMediaUrl(work.getVideoPath()),
                work.getTags(),
                work.isShow(),
                work.getSortOrder(),
                work.getCreatedAt(),
                work.getUpdatedAt()
        );
    }

    private String buildMediaUrl(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        return MediaPathUtils.buildPublicUrl(relativePath, storageProperties.getMediaBaseUrl());
    }
}
