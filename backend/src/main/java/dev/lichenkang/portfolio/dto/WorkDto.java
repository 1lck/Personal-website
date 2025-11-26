package dev.lichenkang.portfolio.dto;

import java.time.Instant;
import java.util.List;

public record WorkDto(
        Long id,
        String name,
        String description,
        String url,
        String imageUrl,
        String videoUrl,
        List<String> tags,
        boolean isShow,
        Integer sortOrder,
        Instant createdAt,
        Instant updatedAt
) {
}
