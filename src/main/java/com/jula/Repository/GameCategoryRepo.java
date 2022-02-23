package com.jula.Repository;

import com.jula.Model.GameCategory;
import org.springframework.data.repository.CrudRepository;

public interface GameCategoryRepo extends CrudRepository<GameCategory, Integer> {
}
