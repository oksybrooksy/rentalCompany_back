package com.jula.Model;


import javax.persistence.*;

@Entity
@Table(name="status")
public class Status {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Status() {
    }
}
