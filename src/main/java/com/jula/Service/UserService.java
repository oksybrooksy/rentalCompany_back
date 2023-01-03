package com.jula.Service;

import com.jula.Model.Game;
import com.jula.Model.Role;
import com.jula.Model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user, int role);
    public List<User> getAllUsers();

    Role saveRole(Role role);
    void addRoleToUser(String email, String role);

    public List<User> getUsers(int role);
    public List<User> findAllBySurname(String surname);

    void delete(int id);
    public User find_user(String email, String haslo);
    public User findUserEmail(String email);

    List<User> getFilteredUsers(String name, int role);
}
