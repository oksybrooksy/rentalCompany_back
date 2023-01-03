package com.jula.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jula.Model.Category;
import com.jula.Model.GameCategory;
import com.jula.Model.Game;
import com.jula.Repository.GameCategoryRepo;
import com.jula.Service.CategoryService;
import com.jula.Service.GameCategoryService;
import com.jula.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@RestController
//@EnableSwagger2
public class GameController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameCategoryRepo gameCategoryRepo;

    @Autowired
   private GameCategoryService gameCategoryService;



    //TODO wyciagac po kategoriach
    @GetMapping("/getAllGames")
    public ResponseEntity getGames(@RequestParam("category") String category, @RequestParam("page") int page, @RequestParam("name") String name) throws JsonProcessingException {


        List<Game> games = gameService.getAllGames(page, name, category);

//        games = gameService.findAllByName("");

        return ResponseEntity.ok(objectMapper.writeValueAsString(games));
    }

    @GetMapping("/getOneGame")
    public ResponseEntity getOneGame(@RequestParam("id") int id) throws JsonProcessingException {
        Game game = gameService.getGame(id);
        return ResponseEntity.ok(objectMapper.writeValueAsString(game));
    }

    @GetMapping("/getOnlyGames")
    public ResponseEntity getGames() throws JsonProcessingException {
        List<Game> games = gameService.getOnlyGames();
        return ResponseEntity.ok(objectMapper.writeValueAsString(games));
    }


    @GetMapping("/test")
    public String test()  {
        return "ok";
    }

    @DeleteMapping("/deleteGame")
    public String deleteGame(@RequestBody IdBody id){
        gameService.delete(id.getId());
        return "ok";
    }
//    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/addGame")
    public String add(@RequestBody Game game){
        game.setRent_amount(0);

        gameService.saveGame(game);
        return "new game added";
    }

    @GetMapping("/findBestGames")
    public List<Game> findBestGames(){
        return gameService.getBestGames();
    }

    public Game getGame(int id){
        return gameService.getGame(id);
    }

}
