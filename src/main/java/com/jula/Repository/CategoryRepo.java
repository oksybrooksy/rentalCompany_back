package com.jula.Repository;

import com.jula.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
