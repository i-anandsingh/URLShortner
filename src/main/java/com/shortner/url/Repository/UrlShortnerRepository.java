package com.shortner.url.Repository;

import com.shortner.url.Entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlShortnerRepository extends JpaRepository <UrlEntity, Long> {
    Optional<UrlEntity> findByOriginalUrl(String originalUrl);
}
