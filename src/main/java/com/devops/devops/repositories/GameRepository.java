package com.devops.devops.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devops.devops.dto.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {


}
