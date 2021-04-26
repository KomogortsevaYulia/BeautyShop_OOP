package com.company;

public class Services {
    private int id;
    private String name;
    private float price;

    public Services(String name,float price){
        this.name=name;
        this.price=price;
    }
    public Services(int id,String name,float price){
        this.id=id;
        this.name=name;
        this.price=price;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
