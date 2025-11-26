package dev.lichenkang.portfolio.repository;

import dev.lichenkang.portfolio.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    Optional<BlogPost> findBySlug(String slug);

    List<BlogPost> findAllByPublishedTrueAndPublishDateBeforeOrderByPublishDateDesc(Instant now);
}
