package com.jula.Model;

import javax.persistence.*;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;

    private int producer_id;
    private int time;
    private int min_players;
    private int max_players;
    private int amount;
    private int rent_amount;
    private String image;
    private String description;
    private boolean is_from_user;
    private int user_id;


    public boolean isIs_from_user() {
        return is_from_user;
    }

    public void setIs_from_user(boolean is_from_user) {
        this.is_from_user = is_from_user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMin_players() {
        return min_players;
    }

    public void setMin_players(int min_players) {
        this.min_players = min_players;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRent_amount() {
        return rent_amount;
    }

    public void setRent_amount(int rent_amount) {
        this.rent_amount = rent_amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game(int id, String name, int producer_id, int time, int min_players, int max_players, int amount, int rent_amount, String image, String description
            , boolean is_from_user, int user_id
    ) {
        this.id = id;
        this.name = name;
        this.producer_id = producer_id;
        this.time = time;
        this.min_players = min_players;
        this.max_players = max_players;
        this.amount = amount;
        this.rent_amount = rent_amount;
        this.image = image;
        this.description = description;
        this.is_from_user = is_from_user;
        this.user_id = user_id;
    }
}
