package dev.lichenkang.portfolio.dto;

import java.time.Instant;
import java.util.Map;

public class ProfileDto {
    private final Long id;
    private final String fullName;
    private final String headline;
    private final String bio;
    private final String email;
    private final String phone;
    private final String wechat;
    private final String location;
    private final String jobTitle;
    private final String heroImageUrl;
    private final String resumeUrl;
    private final String primaryCtaLabel;
    private final String primaryCtaLink;
    private final String secondaryCtaLabel;
    private final String secondaryCtaLink;
    private final Map<String, String> socialLinks;
    private final Instant createdAt;
    private final Instant updatedAt;

    public ProfileDto(Long id,
                      String fullName,
                      String headline,
                      String bio,
                      String email,
                      String phone,
                      String wechat,
                      String location,
                      String jobTitle,
                      String heroImageUrl,
                      String resumeUrl,
                      String primaryCtaLabel,
                      String primaryCtaLink,
                      String secondaryCtaLabel,
                      String secondaryCtaLink,
                      Map<String, String> socialLinks,
                      Instant createdAt,
                      Instant updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.headline = headline;
        this.bio = bio;
        this.email = email;
        this.phone = phone;
        this.wechat = wechat;
        this.location = location;
        this.jobTitle = jobTitle;
        this.heroImageUrl = heroImageUrl;
        this.resumeUrl = resumeUrl;
        this.primaryCtaLabel = primaryCtaLabel;
        this.primaryCtaLink = primaryCtaLink;
        this.secondaryCtaLabel = secondaryCtaLabel;
        this.secondaryCtaLink = secondaryCtaLink;
        this.socialLinks = socialLinks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWechat() {
        return wechat;
    }

    public String getLocation() {
        return location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public String getPrimaryCtaLabel() {
        return primaryCtaLabel;
    }

    public String getPrimaryCtaLink() {
        return primaryCtaLink;
    }

    public String getSecondaryCtaLabel() {
        return secondaryCtaLabel;
    }

    public String getSecondaryCtaLink() {
        return secondaryCtaLink;
    }

    public Map<String, String> getSocialLinks() {
        return socialLinks;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
