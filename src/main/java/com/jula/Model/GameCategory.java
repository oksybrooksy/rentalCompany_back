package com.jula.Model;

import javax.persistence.*;

@Entity
@Table(name="game_category")
public class GameCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int game_id;
    private int category_id;

    public GameCategory(int game_id, int category_id) {
        this.game_id = game_id;
        this.category_id = category_id;
    }

    public GameCategory() {
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int gra_id) {
        this.game_id = gra_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int gatunek_id) {
        this.category_id = gatunek_id;
    }

}
