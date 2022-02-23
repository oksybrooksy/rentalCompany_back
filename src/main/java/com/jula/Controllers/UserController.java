package com.jula.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jula.Model.Game;
import com.jula.Model.User;
import com.jula.Repository.UserRepo;
import com.jula.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

//    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/addUser")
    public String add(@RequestBody User user){
        userService.saveUser(user,1);
        return "new user added";
    }

    @PostMapping("/addEmpl")
    public String addEmployee(@RequestBody User user){
        userService.saveUser(user,2);
        return "new user added";
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getUsers(1);
    }

//    @GetMapping("/getAllUsers")
//    public List<User> getAllUsers(@RequestParam("name") String surname){
//        List<User> users = userService.getUsers(1);
//
//        users = userService.findAllBySurname(surname);
//
//
//
//        return users;
//    }

    @GetMapping("/getAllEmpl")
    public List<User> getAllEmployees(){
        return userService.getUsers(2);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody IdBody id){
        userService.delete(id.getId());
        return "ok";
    }

    @GetMapping("/getFilteredUsers")
    public ResponseEntity getFilteredUsers( @RequestParam("name") String name, @RequestParam("role") int role) throws JsonProcessingException {

        List<User> users = userService.getFilteredUsers(name, role);
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }
//
//    @GetMapping("/logIn")
//    public User logIn(@RequestParam ("email") String email, @RequestParam ("haslo") String haslo){
//        return userService.find_user(email,haslo);
//    }
//
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @CrossOrigin(origins="http://localhost:3000")
//    @GetMapping ("/login")
//    public ResponseEntity myLogin(@RequestParam ("email") String email, @RequestParam ("haslo") String haslo) throws JsonProcessingException {
//        Optional<User> userFromDb = userRepo.findByEmail(email);
//        if(userFromDb.isEmpty()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
////        if(passwordEncoder.matches(haslo,userFromDb.get().getPassword() ) == false){
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        }
//
//        return ResponseEntity.ok(objectMapper.writeValueAsString(userFromDb.get()));
//    }
//
//    @CrossOrigin(origins="http://localhost:3000")
//    @PostMapping ("/myLogin")
//    public ResponseEntity myLogin2(@RequestBody StringObject email, @RequestBody StringObject haslo) throws JsonProcessingException {
//        User user =  userService.find_user(email.getObject(),haslo.getObject());
//        return ResponseEntity.ok(objectMapper.writeValueAsString(user));
//    }

    @PutMapping("/editMail")
    public void editMail(@RequestParam ("newEmail") String newEmail, @RequestBody IdBody id){
        List <User> list = userRepo.findAll();
        for(User user : list){
            if(user.getId() == id.getId()){
                user.setEmail(newEmail);
                userRepo.save(user);
                return;
            }
        }
    }
}
