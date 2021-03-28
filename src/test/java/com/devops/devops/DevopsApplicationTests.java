package com.devops.devops;

import com.devops.devops.dto.Game;
import com.devops.devops.repositories.GameRepository;
import com.devops.devops.repositories.exceptions.GameNotFoundException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.testng.annotations.Test;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
class DevopsApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	GameRepository gamesRepository;

	// get all Games pageable
	@Test
	@GetMapping(value="/games", produces= MediaType.APPLICATION_JSON_VALUE)
	public Page<Game> getAllGames(Pageable pageable) {
		//return all Games
		return gamesRepository.findAll(pageable);
	}


	// get Game by id
	@Test
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
	@Test
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
