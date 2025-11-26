package dev.lichenkang.portfolio.service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import dev.lichenkang.portfolio.dto.HeadingDto;
import dev.lichenkang.portfolio.service.model.MarkdownRenderResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class MarkdownService {

    private final Parser parser;
    private final HtmlRenderer renderer;

    public MarkdownService() {
        MutableDataSet options = new MutableDataSet();
        this.parser = Parser.builder(options).build();
        this.renderer = HtmlRenderer.builder(options).build();
    }

    public MarkdownRenderResult render(String markdown) {
        if (markdown == null || markdown.isBlank()) {
            return new MarkdownRenderResult("", List.of());
        }

        String html = renderer.render(parser.parse(markdown));
        Document document = Jsoup.parseBodyFragment(html);
        List<HeadingDto> headings = new ArrayList<>();

        for (Element heading : document.select("h1, h2, h3")) {
            String id = heading.id();
            if (id == null || id.isBlank()) {
                id = slugify(heading.text());
                heading.attr("id", id);
            }
            int depth = Integer.parseInt(heading.tagName().substring(1));
            headings.add(new HeadingDto(id, heading.text(), depth));
        }

        return new MarkdownRenderResult(document.body().html(), headings);
    }

    private String slugify(String value) {
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String slug = normalized.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("\\s+", "-");
        return slug.isBlank() ? "section-" + UUID.randomUUID() : slug;
    }
}
