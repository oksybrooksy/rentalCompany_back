package com.jula.Service;

import com.jula.Model.GameCategory;
import com.jula.Repository.GameCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameCategoryServiceImpl implements GameCategoryService {
    @Autowired
    private GameCategoryRepo gameCategoryRepo;

    @Override
    public GameCategory saveCategory(GameCategory gameCategory) {
        return gameCategoryRepo.save(gameCategory);
    }

    @Override
    public List<GameCategory> getAllCategories() {
        return (List<GameCategory>) gameCategoryRepo.findAll();
    }
}
