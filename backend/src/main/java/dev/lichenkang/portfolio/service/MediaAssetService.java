package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.dto.MediaAssetDto;
import dev.lichenkang.portfolio.entity.MediaAsset;
import dev.lichenkang.portfolio.repository.MediaAssetRepository;
import dev.lichenkang.portfolio.service.model.StoredFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaAssetService {

    private final MediaAssetRepository mediaAssetRepository;
    private final FileStorageService fileStorageService;

    public MediaAssetService(MediaAssetRepository mediaAssetRepository, FileStorageService fileStorageService) {
        this.mediaAssetRepository = mediaAssetRepository;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    public MediaAssetDto upload(MultipartFile file, String category) {
        StoredFile storedFile = fileStorageService.store(file, category);

        MediaAsset asset = new MediaAsset();
        asset.setOriginalFilename(storedFile.getOriginalFilename());
        asset.setStoredFilename(storedFile.getStoredFileName());
        asset.setRelativePath(storedFile.getRelativePath());
        asset.setMimeType(storedFile.getContentType());
        asset.setSizeInBytes(storedFile.getSize());
        asset.setCategory(category);

        MediaAsset saved = mediaAssetRepository.save(asset);
        return toDto(saved, storedFile.getPublicUrl());
    }

    @Transactional(readOnly = true)
    public List<MediaAssetDto> listAll() {
        return mediaAssetRepository.findAll().stream()
                .map(asset -> toDto(asset, fileStorageService.buildPublicUrl(asset.getRelativePath())))
                .collect(Collectors.toList());
    }

    private MediaAssetDto toDto(MediaAsset asset, String publicUrl) {
        return new MediaAssetDto(
                asset.getId(),
                asset.getOriginalFilename(),
                asset.getRelativePath(),
                publicUrl,
                asset.getMimeType(),
                asset.getSizeInBytes(),
                asset.getCategory(),
                asset.getCreatedAt()
        );
    }

}
