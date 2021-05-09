package com.company.Essence;

public class Record {
    private int id;
    private Services services;
    private Clients clients;
    private Employee employee;
    private String data;
    private String time;
    private String comments;

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
