package com.example.carnow;

public class Booking {

    String model,plate,seater,price, contact,date,day,total;

    public Booking() {
    }

    public Booking(String model, String plate,String seater,String price, String contact, String day, String date,String total ){
        this.model = model;
        this.plate = plate;
        this.seater = seater;
        this.price = price;
        this.contact = contact;
        this.day = day;
        this.date = date;
        this.total = total;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSeater() {
        return seater;
    }

    public void setSeater(String seater) {
        this.seater = seater;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String usage) {
        this.date = usage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

