package com.company.Model;

public class Employee {

    private int id;
    private String surname;     //фамилия
    private String name;        //имя
    private String middle;      //отчетсво
    private String birthdate;   //день рождения
    private String post;       //должность
    private long phone;          //телефон
    //конструктор для записи в БД,тк id-autoincrement
    public Employee(String s,String n,String m,String b,String e,long p){
        this.surname=s;
        this.name=n;
        this.middle=m;
        this.birthdate=b;
        this.post=e;
        this.phone=p;
    }
    //конструктор для вытаскивания из БД
    public Employee(int id,String s,String n,String m,String b,String e,long p){
        this.id=id;
        this.surname=s;
        this.name=n;
        this.middle=m;
        this.birthdate=b;
        this.post=e;
        this.phone=p;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
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

    public long getPhone() {
        return phone;
    }

    public String getPost() {
        return post;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
