package dev.lichenkang.portfolio.entity;

import dev.lichenkang.portfolio.converter.JsonStringMapConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "profile")
public class Profile extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String headline;

    @Column(columnDefinition = "TEXT")
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

    @Convert(converter = JsonStringMapConverter.class)
    private Map<String, String> socialLinks = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
