package com.company.Essence;

public class Clients {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String email;
    private String phone;
    private float point;

    public Clients(int id,String surname,String name,String middle,String birthdate,String email,String phone,float point){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.email=email;
        this.phone=phone;
    }
    public Clients(String surname,String name,String middle,String birthdate,String email,String phone,float point){
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

    public float getPoint() {
        return point;
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

    public String getPhone() {
        return phone;
    }

}
