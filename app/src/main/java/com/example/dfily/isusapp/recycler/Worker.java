package com.example.dfily.isusapp.recycler;

/**
 * Created by dfily on 14.11.2017.
 */

public class Worker {

    private int id;
    private String name;
    private String distance;

    public Worker(int id, String name, String distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
