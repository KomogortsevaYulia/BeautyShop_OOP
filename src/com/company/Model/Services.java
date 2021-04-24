package com.company.Model;

public class Services {
    private int id;
    private String name;
    private float price;

    public Services(int id,String n,float p){
        this.id=id;
        this.name=n;
        this.price=p;
    }
    public Services(String n,float p){
        this.name=n;
        this.price=p;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
