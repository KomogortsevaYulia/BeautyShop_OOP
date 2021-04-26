package com.company;

public class Employee {
    private int id;
    private String surname;
    private String name;
    private String middle;
    private String birthdate;
    private String post;
    private int phone;

    public Employee(int id,String surname,String name,String middle,String birthdate,String post,int phone){
        this.id=id;
        this.surname=surname;
        this.name=name;
        this.middle=middle;
        this.birthdate=birthdate;
        this.post=post;
        this.phone=phone;
    }
    public Employee(String surname,String name,String middle,String birthdate,String post,int phone){
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

    public int getPhone() {
        return phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
