package dev.lichenkang.portfolio.util;

public final class MediaPathUtils {

    private static final String FILES_SEGMENT = "/api/files/";

    private MediaPathUtils() {
    }

    public static String normalize(String path, String mediaBaseUrl) {
        if (path == null || path.isBlank()) {
            return null;
        }
        String normalized = path.trim().replace("\\", "/");
        String base = sanitizeBase(mediaBaseUrl);
        while (!base.isEmpty() && normalized.startsWith(base)) {
            normalized = normalized.substring(base.length());
            normalized = removeLeadingSlash(normalized);
        }

        int markerIndex = normalized.lastIndexOf(FILES_SEGMENT);
        if (markerIndex != -1) {
            normalized = normalized.substring(markerIndex + FILES_SEGMENT.length());
        }

        normalized = removeLeadingSlash(normalized);
        return normalized;
    }

    public static String buildPublicUrl(String relativePath, String mediaBaseUrl) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        String value = relativePath.trim();
        if (value.startsWith("http://") || value.startsWith("https://")) {
            return value;
        }
        String base = sanitizeBase(mediaBaseUrl);
        if (base.isEmpty()) {
            return value;
        }
        value = removeLeadingSlash(value.replace("\\", "/"));
        return base + "/" + value;
    }

    private static String sanitizeBase(String base) {
        if (base == null) {
            return "";
        }
        return base.trim().replaceAll("/+$", "");
    }

    private static String removeLeadingSlash(String value) {
        while (value.startsWith("/")) {
            value = value.substring(1);
        }
        return value;
    }
}
