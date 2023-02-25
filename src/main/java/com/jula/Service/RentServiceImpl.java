package com.jula.Service;

import com.jula.Model.Game;
import com.jula.Model.Rent;
import com.jula.Model.User;
import com.jula.Repository.RentRepo;
import com.jula.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService{
    @Autowired
    private RentRepo rentRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Rent saveRent(Rent rent) {
        rent.setRental_date(Date.valueOf(LocalDate.now()));
        rent.setReturn_date(Date.valueOf(LocalDate.now().plusMonths(1)));
        rent.setStatus_id(1);
        return rentRepo.save(rent);
    }

    @Override
    public List<Rent> getAllRents() {
        return (List<Rent>) rentRepo.findAll();
    }


    @Override
    public void deleteRent(int id) {
        rentRepo.deleteById(id);
    }

    public int editUserRent(int id){
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if(user.getId() == id){
                if(user.getRents_amount() == 3)
                    return -1;
                user.setRents_amount(user.getRents_amount() + 1);
                userRepo.save(user);
            }
        }
        return 0;
    }

    public void lowerUserRent(int id){
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if(user.getId() == id){
                user.setRents_amount(user.getRents_amount() - 1);
                userRepo.save(user);
            }
        }
    }

    @Override
    public void acceptRent(int id) {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for (Rent rent:rents
        ) {
            if(rent.getId()==id && rent.getStatus_id()==1){
                if(editUserRent(rent.getUser_id()) == -1)
                    return;
                rent.setStatus_id(2);
                rentRepo.save(rent);
                return;
            }
        }
    }

    @Override
    public void acceptRentedRent(int id) {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for (Rent rent:rents
        ) {
            if(rent.getId()==id && rent.getStatus_id()==2){
                rent.setStatus_id(4);
                rent.setReturn_date(Date.valueOf(LocalDate.now()));
                rentRepo.save(rent);
                return;
            }
        }
    }

    @Override
    public List<Rent> getUserRents(int id) {
        List<Rent> newRent = new ArrayList<Rent>();
        List<Rent> rents = (List<Rent>) rentRepo.findAll();

        for(Rent rent : rents){
            if(rent.getUser_id() == id && rent.getStatus_id() != 4)
                newRent.add(rent);
        }

        Collections.reverse(newRent);

        return newRent;
    }

    @Override
    public List<Rent> getUserArchieveRents(int id) {
        List<Rent> newRent = new ArrayList<Rent>();
        List<Rent> rents = (List<Rent>) rentRepo.findAll();

        for(Rent rent : rents){
            if(rent.getUser_id() == id && rent.getStatus_id() == 4)
                newRent.add(rent);
        }
        Collections.reverse(newRent);
        return newRent;
    }


    @Override
    public void cancelRent(int id) {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for (Rent rent:rents
        ) {
            if(rent.getId()==id && rent.getStatus_id()==1){
                rent.setStatus_id(3);
                rentRepo.save(rent);
                lowerUserRent(rent.getUser_id());
            }
        }
    }

    @Override
    public Rent userCancelRent(int id) {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for (Rent rent:rents
        ) {
            if(rent.getId()==id && rent.getStatus_id()==1){
                rent.setStatus_id(3);
                lowerUserRent(rent.getUser_id());
                return rentRepo.save(rent);
            }
        }
        return null;
    }
}
