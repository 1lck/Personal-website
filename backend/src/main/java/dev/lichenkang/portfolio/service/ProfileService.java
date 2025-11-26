package dev.lichenkang.portfolio.service;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.dto.ProfileDto;
import dev.lichenkang.portfolio.entity.Profile;
import dev.lichenkang.portfolio.repository.ProfileRepository;
import dev.lichenkang.portfolio.request.ProfileRequest;
import dev.lichenkang.portfolio.util.MediaPathUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ContentStorageProperties storageProperties;

    public ProfileService(ProfileRepository profileRepository, ContentStorageProperties storageProperties) {
        this.profileRepository = profileRepository;
        this.storageProperties = storageProperties;
    }

    @Transactional(readOnly = true)
    public ProfileDto getProfile() {
        return profileRepository.findTopByOrderByIdAsc()
                .map(this::toDto)
                .orElse(null);
    }

    @Transactional
    public ProfileDto upsert(ProfileRequest request) {
        Profile profile = profileRepository.findTopByOrderByIdAsc()
                .orElseGet(Profile::new);
        applyRequest(profile, request);
        return toDto(profileRepository.save(profile));
    }

    private void applyRequest(Profile profile, ProfileRequest request) {
        profile.setFullName(request.getFullName());
        profile.setHeadline(request.getHeadline());
        profile.setBio(request.getBio());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setWechat(request.getWechat());
        profile.setLocation(request.getLocation());
        profile.setJobTitle(request.getJobTitle());
        profile.setHeroImagePath(MediaPathUtils.normalize(request.getHeroImagePath(), storageProperties.getMediaBaseUrl()));
        profile.setResumePath(MediaPathUtils.normalize(request.getResumePath(), storageProperties.getMediaBaseUrl()));
        profile.setPrimaryCtaLabel(request.getPrimaryCtaLabel());
        profile.setPrimaryCtaLink(request.getPrimaryCtaLink());
        profile.setSecondaryCtaLabel(request.getSecondaryCtaLabel());
        profile.setSecondaryCtaLink(request.getSecondaryCtaLink());
        profile.setSocialLinks(request.getSocialLinks());
    }

    private ProfileDto toDto(Profile profile) {
        return new ProfileDto(
                profile.getId(),
                profile.getFullName(),
                profile.getHeadline(),
                profile.getBio(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getWechat(),
                profile.getLocation(),
                profile.getJobTitle(),
                buildMediaUrl(profile.getHeroImagePath()),
                buildMediaUrl(profile.getResumePath()),
                profile.getPrimaryCtaLabel(),
                profile.getPrimaryCtaLink(),
                profile.getSecondaryCtaLabel(),
                profile.getSecondaryCtaLink(),
                profile.getSocialLinks(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }

    private String buildMediaUrl(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        return MediaPathUtils.buildPublicUrl(relativePath, storageProperties.getMediaBaseUrl());
    }
}
