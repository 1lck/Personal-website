package dev.lichenkang.portfolio.repository;

import dev.lichenkang.portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByPublishedTrueOrderBySortOrderAscTitleAsc();
}
