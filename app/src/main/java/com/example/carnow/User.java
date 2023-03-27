package com.example.carnow;

public class User {
    String name, date_birth, lesen, phone,email;

    public User() {
    }

    public User(String name, String date_birth, String lesen, String phone, String email) {
        this.name = name;
        this.date_birth = date_birth;
        this.lesen = lesen;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {

        this.date_birth = date_birth;
    }

    public String getLesen() {

        return lesen;
    }

    public void setLesen(String lesen) {

        this.lesen = lesen;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}


