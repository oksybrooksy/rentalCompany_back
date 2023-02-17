package com.jula.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jula.Model.User;
import com.jula.Repository.UserRepo;
import com.jula.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("/api")
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

    @GetMapping("/user/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getUsers(1);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                com.jula.Model.User user = userService.findUserEmail(username);

                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))//10 minut
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRole_id() )
                        .sign(algorithm);


                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }
        else{
           throw new RuntimeException("Refresh token is missing");
        }

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

    @GetMapping("/admin/getAllEmpl")
    public List<User> getAllEmployees(){
        return userService.getUsers(2);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody IdBody id){
        userService.delete(id.getId());
        return "ok";
    }

    @GetMapping("/admin/getFilteredUsers")
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
