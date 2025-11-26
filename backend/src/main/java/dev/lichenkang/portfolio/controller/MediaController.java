package dev.lichenkang.portfolio.controller;

import dev.lichenkang.portfolio.config.ContentStorageProperties;
import dev.lichenkang.portfolio.dto.MediaAssetDto;
import dev.lichenkang.portfolio.service.FileStorageService;
import dev.lichenkang.portfolio.service.MediaAssetService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class MediaController {

    private static final String HEADER_NAME = "X-ADMIN-TOKEN";

    private final MediaAssetService mediaAssetService;
    private final FileStorageService fileStorageService;
    private final ContentStorageProperties properties;

    public MediaController(MediaAssetService mediaAssetService,
                           FileStorageService fileStorageService,
                           ContentStorageProperties properties) {
        this.mediaAssetService = mediaAssetService;
        this.fileStorageService = fileStorageService;
        this.properties = properties;
    }

    @PostMapping("/api/admin/media/upload")
    public ResponseEntity<MediaAssetDto> upload(@RequestHeader(HEADER_NAME) String token,
                                                @RequestParam("file") MultipartFile file,
                                                @RequestParam(value = "category", required = false, defaultValue = "misc") String category) {
        ensureToken(token);
        return ResponseEntity.ok(mediaAssetService.upload(file, category));
    }

    @GetMapping("/api/files/**")
    public ResponseEntity<Resource> serve(HttpServletRequest request) throws IOException {
        String requestPath = request.getRequestURI();
        String filePath = requestPath.substring("/api/files/".length());
        
        Resource resource = fileStorageService.loadAsResource(filePath);
        if (!resource.exists() || !resource.isReadable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "文件不存在");
        }
        
        String contentType = Files.probeContentType(resource.getFile().toPath());
        MediaType mediaType = contentType == null ? MediaType.APPLICATION_OCTET_STREAM : MediaType.parseMediaType(contentType);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .contentType(mediaType)
                .body(resource);
    }

    private void ensureToken(String token) {
        String expected = properties.getAdminToken();
        if (token == null || token.isBlank() || !token.equals(expected)) {
            throw new ResponseStatusException(UNAUTHORIZED, "无效的后台 Token");
        }
    }
}
