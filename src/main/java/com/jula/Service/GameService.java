package com.jula.Service;

import com.jula.Model.Game;

import java.util.List;

public interface GameService {
    public void saveGame(Game game);
    public List<Game> getAllGames(int page, String name, String category);
    public List<Game> getAll();
    public List<Game> findAllByName(String name);
    void delete(int id );
    List<Game> getBestGames();
    List<Game> getOnlyGames();
    Game getGame(int id);

}
