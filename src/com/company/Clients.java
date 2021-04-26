package com.company;

public class Clients {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String email;
    private int phone;

    public Clients(int id,String surname,String name,String middle,String birthdate,String email,int phone){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.email=email;
        this.phone=phone;
    }
    public Clients(String surname,String name,String middle,String birthdate,String email,int phone){
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.email=email;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMiddle() {
        return middle;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
