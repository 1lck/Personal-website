package dev.lichenkang.portfolio.repository;

import dev.lichenkang.portfolio.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findTopByOrderByIdAsc();
}
