package com.news.service.mathplayopen.infrastructure.persistance.jpa.respositories;

import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.valueobjects.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(Title title);
}
