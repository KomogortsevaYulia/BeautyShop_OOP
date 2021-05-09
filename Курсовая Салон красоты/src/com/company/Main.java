package com.company;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Record;
import com.company.Essence.Services;
import com.company.Model.DBWorker;
import com.company.View.MainFrame;
import org.sqlite.core.DB;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*DBWorker.initDB();
        DBWorker.addRecord(new Record(
                20,
                new Services(
                        10,
                        "name" ,
                        40),
                new Clients(
                        100,
                        "surname_clients" ,
                        "name_clients",
                        "middle_clients",
                        "birhdate",
                        "email",
                        "phone"),
                new Employee(
                        80,
                        "surname_employee" ,
                        "name_employee",
                        "middle_employee",
                        "birhdate",
                        "post",
                        "phone"),
                "date",
                "time",
                "comment"
        ));
        DBWorker.addPerformedWork(new Record(
                20,
                new Services(
                        10,
                        "name" ,
                        40),
                new Clients(
                        100,
                        "surname_clients" ,
                        "name_clients",
                        "middle_clients",
                        "birhdate",
                        "email",
                        "phone"),
                new Employee(
                        80,
                        "surname_employee" ,
                        "name_employee",
                        "middle_employee",
                        "birhdate",
                        "post",
                        "phone"),
                "date",
                "time",
                "comment"
        ));
        DBWorker.addServices(new Services("dfxbgdfb",500));
        DBWorker.addServices(new Services("serf",1500));
        DBWorker.addServices(new Services("rfe",1000));
        DBWorker.addServices(new Services("tg",5000));
        DBWorker.addServices(new Services("dfxbgdfb",500));
        DBWorker.addServices(new Services("serf",1500));
        DBWorker.addServices(new Services("rfe",1000));
        DBWorker.addServices(new Services("tg",5000));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd",  "89144767878"));
        DBWorker.addEmployee(new Employee("myjgv","hy","ghj","gj","gj",  "89144767878"));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd",  "89144767878"));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd",  "89144767878"));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd",  "89144767878"));
        DBWorker.addEmployee(new Employee("myjgv","hy","ghj","gj","gj", "89144767878"));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd", "89144767878"));
        DBWorker.addEmployee(new Employee("xfgb","xdgb","xdf","dxbf","dxbfd",  "89144767878"));
        DBWorker.addClients(new Clients("df","xd","fb","xdf","bbd", "89144767878"));
        DBWorker.addClients(new Clients("tjf","fh","cfb","cfgh","cf",  "89144767878"));
        DBWorker.addClients(new Clients("tht","zsw","fb","xdf","cfg", "89144767878"));
        DBWorker.addClients(new Clients("df","xd","fb","xdf","bbd",  "89144767878"));
        DBWorker.addClients(new Clients("df","xd","fb","xdf","bbd", "89144767878"));
        DBWorker.addClients(new Clients("tjf","fh","cfb","cfgh","cf",  "89144767878"));
        DBWorker.addClients(new Clients("tht","zsw","fb","xdf","cfg", "89144767878"));
        DBWorker.addClients(new Clients("df","xd","fb","xdf","bbd",  "89144767878"));
        DBWorker.closeDB();*/
        MainFrame m=new MainFrame();
    }
}
