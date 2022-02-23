package com.jula.Service;

import com.jula.Model.Game;
import com.jula.Model.Rent;
import com.jula.Repository.RentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{
    @Autowired
    private RentRepo rentRepo;

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

    @Override
    public void acceptRent(int id) {
        List<Rent> rents = (List<Rent>) rentRepo.findAll();
        for (Rent rent:rents
        ) {
            if(rent.getId()==id && rent.getStatus_id()==1){
                rent.setStatus_id(2);
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
            if(rent.getUser_id() == id)
                newRent.add(rent);
        }
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
                return rentRepo.save(rent);
            }
        }
        return null;
    }
}
