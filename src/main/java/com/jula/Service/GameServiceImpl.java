package com.jula.Service;

import com.jula.Model.Category;
import com.jula.Model.Game;
import com.jula.Model.GameCategory;
import com.jula.Model.Rent;
import com.jula.Repository.GameRepo;
import com.jula.Repository.RentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private RentRepo rentRepo;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GameCategoryService gameCategoryService;

    @Override
    public Game saveGame(Game game) {
        return gameRepo.save(game);
    }

    private int getCategoryId(String category){
        List<Category> categories = categoryService.getAllCategoryNames();

        for(Category category1 : categories){
            if(category1.getName().equals(category))
                return category1.getId();
        }

        return -1;
    }

//    @Autowired
//    private GameService gameService;

    @Override
    public List<Game> getAllGames(int page, String name, String category) {
//        int totalGames = ((List<Game>)gameRepo.findAll()).size();
//        List<Game> paginated;
        Pageable pageItems;

        pageItems = PageRequest.of(page, 15);

        int categoryId = getCategoryId(category);
        if(categoryId == -1){
            return  gameRepo.findAllByNameContaining(name,pageItems);
        }

        List<Game> gamesWithName = gameRepo.findAllByNameContaining(name);

        List<Game> games = new LinkedList<>();
        List<GameCategory> gameCategory = gameCategoryService.getAllCategories();
        int gameId = -1;

        for(GameCategory gameCategory1 : gameCategory){
            if(gameCategory1.getCategory_id() == categoryId){
                gameId = gameCategory1.getGame_id();
                if(gamesWithName.contains(getGame(gameId)))
                    games.add(getGame(gameId));
            }
        }

        return games;

//        return  gameRepo.findAllByNameContaining("",pageItems);
    }

    @Override
    public List<Game> getAll() {
        return (List<Game>) gameRepo.findAll();
    }

    @Override
    public List<Game> findAllByName(String name) {
        return gameRepo.findAllByNameContaining(name);
    }

    @Override
    public void delete(int id) {
        Iterable<Rent> rentRepos = rentRepo.findAll();

        rentRepos.forEach(rent -> {
            if(rent.getGame_id() == id){
                rentRepo.delete(rent);
            }
        });
        gameRepo.deleteById(id);
    }

    @Override
    public List<Game> getBestGames() {
        List<Game> games = getAll();
        List<Game> bestGames = new LinkedList<>() ;

        int max = 0;
        Game maxGame = games.get(0);
        Game secondGame = maxGame;
        Game thirdGame = secondGame;

        for (Game game : games) {
            if(game.getRent_amount() > max){
                thirdGame = secondGame;
                secondGame = maxGame;
               maxGame = game;
               max = game.getRent_amount();
            }
            else{
                if(game.getRent_amount() > secondGame.getRent_amount()){
                    thirdGame = secondGame;
                    secondGame = game;
                }
            }
        }
        bestGames.add(maxGame);
        bestGames.add(secondGame);
        bestGames.add(thirdGame);


        return bestGames;
    }

    @Override
    public List<Game> getOnlyGames() {
         return (List<Game>) gameRepo.findAll();
    }

    @Override
    public Game getGame(int id) {
        List<Game> games = getAll();
        for (Game game : games) {
            if(game.getId() == id)
                return game;
        }
        return null;
    }
}
