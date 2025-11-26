package dev.lichenkang.portfolio.repository;

import dev.lichenkang.portfolio.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findAllByOrderBySortOrderAsc();
    List<Work> findByIsShowTrueOrderBySortOrderAsc();
}
