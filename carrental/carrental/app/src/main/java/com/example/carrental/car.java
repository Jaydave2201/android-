package com.example.carrental;

public class car {
    private String name;
    private String gear;
    private String color;
    private int seat;
    private String fuel;
    private String ac;
    private String price;
    private String image; // Added image field

    public car(String name, String gear, String color, int seat, String fuel, String ac, String price, int image) {
        this.name = name;
        this.gear = gear;
        this.color = color;
        this.seat = seat;
        this.fuel = fuel;
        this.ac = ac;
        this.price = price;
        this.image = String.valueOf(image);
    }

    public String getName() {
        return name;
    }

    public String getGear() {
        return gear;
    }

    public String getColor() {
        return color;
    }

    public int getSeat() {
        return seat;
    }

    public String getFuel() {
        return fuel;
    }

    public String getAc() {
        return ac;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
