package com.jula.Service;

import com.jula.Model.Rent;
import com.jula.Model.Role;
import com.jula.Model.User;
import com.jula.Repository.RentRepo;
import com.jula.Repository.RoleRepo;
import com.jula.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.get().getRole_id() == 1){
            authorities.add(new SimpleGrantedAuthority("uzytkownik"));
        }
        else if(user.get().getRole_id() == 2){
            authorities.add(new SimpleGrantedAuthority("pracownik"));
        }
        else if(user.get().getRole_id() == 3){
            authorities.add(new SimpleGrantedAuthority("administrator"));
        }
//        user.get().getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        });

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
    }

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
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
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String role) {
    Optional<User> user = userRepo.findByEmail(email);
    Role newRole = roleRepo.findByRole(role);
  //  user.get().getRoles().add(newRole);
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

    @Override
    public User getUser(String username) {
        return userRepo.findByEmail(username).get();
    }


}
