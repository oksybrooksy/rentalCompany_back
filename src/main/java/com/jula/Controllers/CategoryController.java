package com.jula.Controllers;

import com.jula.Model.Category;
import com.jula.Model.GameCategory;
import com.jula.Service.CategoryService;
import com.jula.Service.GameCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryNames")
    public List<Category> getCategoryNames(){
        return categoryService.getAllCategoryNames();
    }
}
