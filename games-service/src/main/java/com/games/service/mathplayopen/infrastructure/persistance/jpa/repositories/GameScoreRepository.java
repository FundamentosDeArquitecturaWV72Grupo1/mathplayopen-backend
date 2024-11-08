package com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.games.service.mathplayopen.domain.model.entities.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
    Optional<GameScore> findByStudentId(Long studentId);
}
