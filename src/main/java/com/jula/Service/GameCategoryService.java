package com.jula.Service;

import com.jula.Model.GameCategory;

import java.util.List;

public interface GameCategoryService {
    public GameCategory saveCategory(GameCategory gameCategory);
    public List<GameCategory> getAllCategories();
}
