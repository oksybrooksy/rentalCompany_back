package com.jula.Repository;

import com.jula.Model.Game;
import com.jula.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String  email);

    List<User> findAllBySurnameContaining(String surname);
}
