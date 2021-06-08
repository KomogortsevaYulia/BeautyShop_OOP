package com.company.Essence;

import java.util.Objects;

public class Record {
    private int id;
    private Services services;
    private Clients clients;
    private Employee employee;
    private String data;
    private String time;
    private String comments;


    public boolean equals(Record r) {
        if(this.getId()==r.getId()&
            this.getClients().equals(r.getClients())
                &this.getServices().equals(r.getServices())
                &this.getEmployee().equals(r.getEmployee())
                &this.getComments().equals(r.getComments())
                &this.getTime().equals(r.getTime())
                &this.getData().equals(r.getData())

        ){
            return true;
        }
        else{
            return false;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public Record(int id, Services s, Clients c, Employee e, String data, String time, String comments)
    {
        this.id=id;
        this.services=s;
        this.clients=c;
        this.employee=e;
        this.data=data;
        this.time=time;
        this.comments=comments;
    }
    public Record(Services s, Clients c, Employee e, String data, String time, String comments){
        this.services=s;
        this.clients=c;
        this.employee=e;
        this.data=data;
        this.time=time;
        this.comments=comments;
    }

    public int getId() {
        return id;
    }

    public Clients getClients() {
        return clients;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Services getServices() {
        return services;
    }

    public String getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    public String getComments() {
        return comments;
    }
}
