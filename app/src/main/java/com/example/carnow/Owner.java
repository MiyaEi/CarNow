package com.example.carnow;

public class Owner {

    private String phone,plate,seater,price,model,link;

    public Owner() {
    }

    public Owner(String link,String phone, String plate, String model, String seater, String price) {
        this.phone = phone;
        this.plate = plate;
        this.model = model;
        this.seater = seater;
        this.price = price;
        this.link = link;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getSeater() {
        return seater;
    }

    public void setSeater(String seater) {
        this.seater = seater;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
