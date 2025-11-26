package dev.lichenkang.portfolio.dto;

public class HeadingDto {
    private final String slug;
    private final String text;
    private final int depth;

    public HeadingDto(String slug, String text, int depth) {
        this.slug = slug;
        this.text = text;
        this.depth = depth;
    }

    public String getSlug() {
        return slug;
    }

    public String getText() {
        return text;
    }

    public int getDepth() {
        return depth;
    }
}
