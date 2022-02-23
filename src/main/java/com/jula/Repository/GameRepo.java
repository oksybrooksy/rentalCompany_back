package com.jula.Repository;

import com.jula.Model.Category;
import com.jula.Model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface GameRepo extends CrudRepository<Game, Integer>{

    List<Game> findAllByNameContaining(String name, Pageable page);
    List<Game> findAllByNameContaining(String name);
}


