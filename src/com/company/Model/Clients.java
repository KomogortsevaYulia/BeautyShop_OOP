package com.company.Model;

public class Clients {
    private int id;
    private String surname;     //фамилия
    private String name;        //имя
    private String middle;      //отчетсво
    private String birthdate;   //день рождения
    private String email;       //почта
    private long phone;          //телефон
    //конструктор для записи в БД,тк id-autoincrement
    public Clients(String s,String n,String m,String b,String e,long p){
        this.surname=s;
        this.name=n;
        this.middle=m;
        this.birthdate=b;
        this.email=e;
        this.phone=p;
    }
    //конструктор для вытаскивания из БД
    public Clients(int id,String s,String n,String m,String b,String e,long p){
        this.id=id;
        this.surname=s;
        this.name=n;
        this.middle=m;
        this.birthdate=b;
        this.email=e;
        this.phone=p;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
