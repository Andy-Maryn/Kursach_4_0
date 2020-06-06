package com.example.kursach_4_0.orm;

public class MyTown {

    private int id;
    private String town;

    public MyTown(int id, String town) {
        this.id = id;
        this.town = town;
    }

    public MyTown(String town) {
        this.town = town;
    }

    public MyTown() {
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String town) {
        this.town = town;
    }

    public String getName() {
        return this.town;
    }

    public int getID() {
        return this.id;
    }
}
