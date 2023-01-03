package com.jula.Repository;

import com.jula.Model.Category;
import com.jula.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
