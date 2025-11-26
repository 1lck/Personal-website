package dev.lichenkang.portfolio.request;

import jakarta.validation.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

public class ProfileRequest {

    @NotBlank
    private String fullName;

    private String headline;

    private String bio;

    private String email;

    private String phone;

    private String wechat;

    private String location;

    private String jobTitle;

    private String heroImagePath;

    private String resumePath;

    private String primaryCtaLabel;

    private String primaryCtaLink;

    private String secondaryCtaLabel;

    private String secondaryCtaLink;

    private Map<String, String> socialLinks = new HashMap<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getHeroImagePath() {
        return heroImagePath;
    }

    public void setHeroImagePath(String heroImagePath) {
        this.heroImagePath = heroImagePath;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getPrimaryCtaLabel() {
        return primaryCtaLabel;
    }

    public void setPrimaryCtaLabel(String primaryCtaLabel) {
        this.primaryCtaLabel = primaryCtaLabel;
    }

    public String getPrimaryCtaLink() {
        return primaryCtaLink;
    }

    public void setPrimaryCtaLink(String primaryCtaLink) {
        this.primaryCtaLink = primaryCtaLink;
    }

    public String getSecondaryCtaLabel() {
        return secondaryCtaLabel;
    }

    public void setSecondaryCtaLabel(String secondaryCtaLabel) {
        this.secondaryCtaLabel = secondaryCtaLabel;
    }

    public String getSecondaryCtaLink() {
        return secondaryCtaLink;
    }

    public void setSecondaryCtaLink(String secondaryCtaLink) {
        this.secondaryCtaLink = secondaryCtaLink;
    }

    public Map<String, String> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(Map<String, String> socialLinks) {
        this.socialLinks = socialLinks;
    }
}
