package com.jula.Repository;

import com.jula.Model.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepo extends CrudRepository<Rent, Integer> {
}
