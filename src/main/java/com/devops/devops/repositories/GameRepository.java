package com.devops.devops.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devops.devops.dto.Game;

import javax.persistence.EntityManager;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
