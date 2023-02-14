package com.jula.Service;

import com.jula.Model.Rent;

import java.util.List;

public interface RentService {
    public Rent saveRent(Rent rent);
    public List<Rent> getAllRents();
    public void deleteRent(int id);

    public void acceptRent(int id);
    public void acceptRentedRent(int id);


    public void cancelRent(int id);
    public Rent userCancelRent(int id);
    List<Rent> getUserRents(int id);
}
