package com.company.Essence;

public class Employee {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String post;
    private String phone;

    public Employee(int id,String surname,String name,String middle,String birthdate,String post,String phone){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.post=post;
        this.phone=phone;
    }
    public Employee(String surname,String name,String middle,String birthdate,String post,String phone){
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.post=post;
        this.phone=phone;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getMiddle() {
        return middle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getPhone() {
        return phone;
    }

}
