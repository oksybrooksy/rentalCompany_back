package com.jula.Repository;

import com.jula.Model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends CrudRepository<Status, Integer> {
}
