package com.jula.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="rental")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int game_id;
    private int user_id;
    private Date return_date;
    private Date rental_date;
    private int status_id;

    public Rent() {
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public Rent(int id, int game_id, int user_id, Date return_date, Date rental_date, int status_id) {
        this.id = id;
        this.game_id = game_id;
        this.user_id = user_id;
        this.return_date = return_date;
        this.rental_date = rental_date;
        this.status_id = status_id;
    }
}
