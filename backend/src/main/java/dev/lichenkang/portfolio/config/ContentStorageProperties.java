package dev.lichenkang.portfolio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "content")
public class ContentStorageProperties {

    /**
     * 媒体文件存储根目录
     */
    private String storageRoot = "storage/media";

    /**
     * 媒体文件访问基地址
     */
    private String mediaBaseUrl = "http://localhost:8080/api/files";

    /**
     * 后台管理 Token
     */
    private String adminToken = "dev-token";

    /**
     * 允许的 CORS 来源
     */
    private List<String> allowedOrigins = new ArrayList<>();

    public String getStorageRoot() {
        return storageRoot;
    }

    public void setStorageRoot(String storageRoot) {
        this.storageRoot = storageRoot;
    }

    public String getMediaBaseUrl() {
        return mediaBaseUrl;
    }

    public void setMediaBaseUrl(String mediaBaseUrl) {
        this.mediaBaseUrl = mediaBaseUrl;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
