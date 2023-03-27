package com.example.carnow;

public class Car {
    String car_name;
    int img_car;

    public Car(String name, int image) {
        this.car_name = name;
        this.img_car = image;
    }

    public String getCar_name() {
        return car_name;
    }

    public int getImg_car() {
        return img_car;
    }

    public int getImage(){
        return img_car;
    }

    public void setImage(int image) {
        this.img_car = image;
    }
}
