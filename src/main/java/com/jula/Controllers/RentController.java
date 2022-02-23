package com.jula.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jula.Model.Game;
import com.jula.Model.Rent;
import com.jula.Model.User;
import com.jula.Repository.GameRepo;
import com.jula.Repository.RentRepo;
import com.jula.Repository.UserRepo;
import com.jula.Service.GameService;
import com.jula.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RentRepo rentRepo;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepo gameRepo;

    @GetMapping("/getAllRents")
    public List<Rent> getAllRents(){
        return rentService.getAllRents();
    }

    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/getUserRent")
    public List<Rent> getUserRents(@RequestBody IdBody id){
        return rentService.getUserRents(id.getId());
    }

    @DeleteMapping("/deleteRent")
    public String deleteRent(@RequestBody IdBody id){
        rentService.deleteRent(id.getId());
        return "ok";
    }


    //    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/acceptRent")
    public void acceptRent(@RequestBody IdBody id){
        rentService.acceptRent(id.getId());
    }

    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/cancelRent")
    public void cancelRent(@RequestBody IdBody id){
        rentService.cancelRent(id.getId());
    }

    //    @CrossOrigin(origins="http://localhost:3000")
//    @PutMapping("/userCancelRent")
//    public void cancelRentByUser(@RequestBody IdBody id){
//        rentService.userCancelRent(id.getId());
//    }


//    @PostMapping("/addRent")
//    public String add(@RequestBody Rent rent){
//        rentService.saveRent(rent);
//        rent.setUser_id(52);
//        rent.setGame_id(3);
//        rent.setRental_date(Date.valueOf(LocalDate.now()));
//        rent.setReturn_date(Date.valueOf(LocalDate.now().plusMonths(1)));
//        rent.setStatus_id(1);
//        return "new rent added";
//    }

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping("/newRent")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity addRent(@RequestParam ("email") String email,@RequestParam ("graId")int graId) throws JsonProcessingException, JsonProcessingException {

        Optional<User> userFromDb = userRepo.findByEmail(email);
        if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Rent rent = new Rent();
        rent.setUser_id(userFromDb.get().getId());
        rent.setGame_id(graId);
        rent.setRental_date(Date.valueOf(LocalDate.now()));
        rent.setReturn_date(Date.valueOf(LocalDate.now().plusMonths(1)));
        rent.setStatus_id(1);
        rentRepo.save(rent);

        Game game = gameService.getGame(graId);
        int rents = game.getRent_amount() + 1;
        game.setRent_amount(rents);
        gameRepo.save(game);

        return ResponseEntity.ok(objectMapper.writeValueAsString(rent));
    }

    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/userCancelRent")
    public ResponseEntity newUserCancelRent(@RequestBody IdBody id) throws JsonProcessingException {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for(Rent rent : rents){
            if(rent.getId()==id.getId() && rent.getStatus_id()==1 ){
                rent.setStatus_id(3);
                rentRepo.save(rent);
                return ResponseEntity.ok(objectMapper.writeValueAsString(rent));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
