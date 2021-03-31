package com.devops.devops;

import com.devops.devops.dto.Game;
import com.devops.devops.repositories.GameRepository;
import com.devops.devops.repositories.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import java.util.Optional;

@SpringBootApplication
public class DevopsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(DevopsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(
				DevopsApplication.class);
		sa.run(args);
	}

	@RestController
	public static class WarInitializerController {

		@Autowired
		GameRepository gamesRepository;

		@GetMapping(value="/games", produces= MediaType.APPLICATION_JSON_VALUE)
		public Page<Game> getAllGames(Pageable pageable) {
			//return all Games
			return gamesRepository.findAll(pageable);
		}

		// get Game by id
		@GetMapping("games/{id}")
		public Game getGameById(@PathVariable int id) {
			// return Game by id
			Optional<Game> foundGame = gamesRepository.findById(id);
			if(foundGame.isPresent())
				return foundGame.get();
			else
				throw new GameNotFoundException("Unable to find Game with id: " + id);
		}

		//Delete Game by id
		@DeleteMapping("games/{id}")
		public void deleteGameById(@PathVariable int id) {

			try {
				// delete by id
				gamesRepository.deleteById(id);
			}
			catch (EmptyResultDataAccessException e) {
				throw new GameNotFoundException("Unable to delete Game with id:" + id);
			}
		}
	}
}
