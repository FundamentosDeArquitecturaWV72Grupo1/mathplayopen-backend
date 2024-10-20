package com.games.service.mathplayopen.infrastructure.persistance.jpa.repositories;

import com.games.service.mathplayopen.domain.model.aggregates.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
