package com.epam.dogsapp;

import java.io.Serializable;

public class Dog implements Serializable{

    private int id;
    private String name;
    private String breed;
    private Integer weight;

    public Dog() {

    }

    public Dog(int id, String name, String breed, int weight) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
    }

    public Dog(String name, String breed, int weight) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("[%s: %s / %s / %s]", id, name, breed, weight);
    }
}
