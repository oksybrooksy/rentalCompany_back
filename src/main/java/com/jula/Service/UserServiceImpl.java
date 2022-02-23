package com.jula.Service;

import com.jula.Model.Rent;
import com.jula.Model.User;
import com.jula.Repository.RentRepo;
import com.jula.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private RentRepo rentRepo;

    @Override
    public User saveUser(User user, int role) {
        String encodedPassword =  this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole_id(role);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getUsers(int role) {
        List<User> users = new LinkedList<>();
        List<User> all = userRepo.findAll();

        for(User user : all){
            if(user.getRole_id() == role)
                users.add(user);
        }
        return users;
    }

    @Override
    public List<User> findAllBySurname(String surname) {
        return userRepo.findAllBySurnameContaining(surname);
    }


    @Override
    public void delete(int id) {
        Iterable<Rent> rentRepos = rentRepo.findAll();

        rentRepos.forEach(rent -> {
            if(rent.getUser_id() == id){
                rentRepo.delete(rent);
            }
        });

        userRepo.deleteById(id);
    }

    @Override
    public User find_user(String email, String haslo) {
        return userRepo.findByEmailAndPassword(email,haslo).get();
    }

    @Override
    public User findUserEmail(String email) {
        return userRepo.findByEmail(email).get();
    }

    @Override
    public List<User> getFilteredUsers(String name, int role) {
        List<User> all =  userRepo.findAllBySurnameContaining(name);

        List<User> users = new LinkedList<>();

        for(User user : all){
            if(user.getRole_id() == role)
                users.add(user);
        }

        return users;
    }
}
