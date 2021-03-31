package com.devops.devops.repositories;

import com.devops.devops.dto.Game;
import javax.persistence.EntityManager;

public class GameRepositoriesTest {


    public EntityManager em;

    public Game add(final Game game) {
        em.persist(game);
        return game;
    }

    public Game findById(final int id) {
        return em.find(Game.class,  id);
    }
}
