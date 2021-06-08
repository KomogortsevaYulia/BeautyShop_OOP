package com.company.Essence;

public class Services {
    private String name;
    private float price;

    public Services(String name,float price){
        this.name=name;
        this.price=price;
    }
    public boolean equals(Services S) {
        if(this.getName().equals(S.getName())&
                this.getPrice()==S.getPrice()
        ){
            return true;
        }
        else{
            return false;
        }
    }
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
