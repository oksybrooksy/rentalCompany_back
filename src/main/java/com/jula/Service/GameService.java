package com.jula.Service;

import com.jula.Model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    public Game saveGame(Game game);
    public List<Game> getAllGames(int page, String name, String category);
    public List<Game> getAll();

    public List<Game> findAllByName(String name);


    void delete(int id );

    List<Game> getBestGames();


    List<Game> getOnlyGames();

    Game getGame(int id);

}
