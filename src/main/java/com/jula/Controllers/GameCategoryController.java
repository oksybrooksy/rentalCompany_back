package com.jula.Controllers;

import com.jula.Model.GameCategory;
import com.jula.Service.GameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GameCategoryController {
    @Autowired
    private GameCategoryService gameCategoryService;

    @GetMapping("/getAllCategories")
    public List<GameCategory> getAllCategories(){
        return gameCategoryService.getAllCategories();
    }



    @PostMapping("/addCategory")
    public String addCategory(@RequestBody GameCategory gameCategory){
        gameCategoryService.saveCategory(gameCategory);
        return "new category added";
    }
}
