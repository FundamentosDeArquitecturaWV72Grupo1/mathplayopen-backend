package com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.games.service.mathplayopen.domain.model.entities.FavoriteGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteGameRepository extends JpaRepository<FavoriteGame, Long> {
    List<FavoriteGame> findByStudentId(Long studentId);
    Optional<FavoriteGame> findByGameIdAndStudentId(Long gameId, Long studentId);
}
