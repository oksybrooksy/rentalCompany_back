package com.jula.Service;

import com.jula.Model.Category;
import com.jula.Model.GameCategory;
import com.jula.Repository.CategoryRepo;
import com.jula.Repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategoryNames() {
        return  (List<Category>) categoryRepo.findAll();
    }
}
