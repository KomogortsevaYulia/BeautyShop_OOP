package com.company.Model;

import com.company.Essence.*;
import com.company.Essence.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE="BeautyShop.db";
    public static final String URL="jdbc:sqlite:"+PATH_TO_DB_FILE;
    public static Connection connection;

    public static void initDB(){
        try {
            connection= DriverManager.getConnection(URL);
            if (connection!=null){
                DatabaseMetaData metaData=connection.getMetaData();
                DBWorker.createDB();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeDB(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createDB(){
        try {
            Statement statement=connection.createStatement();
            statement.execute("CREATE TABLE if not exists 'rewards' " +
                    "(" +
                    " 'transfersPoint' int NOT NULL," +
                    " 'discountPoint' int NOT NULL);");
            statement.execute("CREATE TABLE if not exists 'services' " +
                    "(" +
                    " 'name' text PRIMARY KEY NOT NULL," +
                    " 'price' float NOT NULL);");
            statement.execute("CREATE TABLE if not exists 'clients' " +
                    "(" +
                    " 'id_clients' INTEGER PRIMARY  KEY AUTOINCREMENT," +
                    " 'surname_clients' text," +
                    " 'name_clients' text NOT NULL," +
                    " 'middle_clients' text," +
                    " 'birhdate' text," +
                    " 'email' text," +
                    " 'phone' text NOT NULL," +
                    " 'point' float );");
            statement.execute("CREATE TABLE if not exists 'employee' " +
                    "(" +
                    " 'id_employee' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'surname_employee' text," +
                    " 'name_employee' text NOT NULL," +
                    " 'middle_employee' text," +
                    " 'birhdate' text," +
                    " 'post' text," +
                    " 'phone' text NOT NULL);");
            statement.execute("CREATE TABLE if not exists 'performedWork' " +
                    "(" +
                    " 'id_work' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'name' text NOT NULL," +
                    " 'price' float NOT NULL,"+
                    " 'point' float ,"+//сколько баллов спишется
                    " 'income' float ,"+//доход=прайс-пойнт
                    " 'id_clients' INTEGER ," +
                    " 'surname_clients' text," +
                    " 'name_clients' text NOT NULL," +
                    " 'middle_clients' text," +
                    " 'id_employee' INTEGER ," +
                    " 'surname_employee' text," +
                    " 'name_employee' text NOT NULL," +
                    " 'middle_employee' text," +
                    " 'date' text," +
                    " 'time' text," +
                    " 'comment' text );");
            statement.execute("CREATE TABLE if not exists 'record' " +
                    "(" +
                    " 'id_record' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'name' text NOT NULL," +
                    " 'price' float NOT NULL,"+
                    " 'id_clients' INTEGER ," +
                    " 'surname_clients' text," +
                    " 'name_clients' text NOT NULL," +
                    " 'middle_clients' text," +
                    " 'id_employee' INTEGER ," +
                    " 'surname_employee' text," +
                    " 'name_employee' text NOT NULL," +
                    " 'middle_employee' text," +
                    " 'date' text," +
                    " 'time' text," +
                    " 'comment' text );");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addPerformedWork(Work p){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO performedWork (" +
                    "'name'," +
                    "'price'," +
                    " 'point' ,"+
                    "'income' ,"+
                    "'id_clients'," +
                    "'surname_clients'," +
                    "'name_clients'," +
                    "'middle_clients'," +
                    "'id_employee'," +
                    "'surname_employee'," +
                    "'name_employee'," +
                    "'middle_employee'," +
                    "'date'," +
                    "'time'," +
                    "'comment') "+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            S.setObject(1,p.getServices().getName());
            S.setObject(2,p.getServices().getPrice());
            S.setObject(3,p.getPoint());
            S.setObject(4,p.getServices().getPrice()-p.getPoint());
            S.setObject(5,p.getClients().getId());
            S.setObject(6,p.getClients().getSurname());
            S.setObject(7,p.getClients().getName());
            S.setObject(8,p.getClients().getMiddle());
            S.setObject(9,p.getEmployee().getId());
            S.setObject(10,p.getEmployee().getSurname());
            S.setObject(11,p.getEmployee().getName());
            S.setObject(12,p.getEmployee().getMiddle());
            S.setObject(13,p.getData());
            S.setObject(14,p.getTime());
            S.setObject(15,p.getComments());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addRecord(Record p){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO record (" +
                    "'name'," +
                    "'price'," +
                    "'id_clients'," +
                    "'surname_clients'," +
                    "'name_clients'," +
                    "'middle_clients'," +
                    "'id_employee'," +
                    "'surname_employee'," +
                    "'name_employee'," +
                    "'middle_employee'," +
                    "'date'," +
                    "'time'," +
                    "'comment') "+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            S.setObject(1,p.getServices().getName());
            S.setObject(2,p.getServices().getPrice());
            S.setObject(3,p.getClients().getId());
            S.setObject(4,p.getClients().getSurname());
            S.setObject(5,p.getClients().getName());
            S.setObject(6,p.getClients().getMiddle());
            S.setObject(7,p.getEmployee().getId());
            S.setObject(8,p.getEmployee().getSurname());
            S.setObject(9,p.getEmployee().getName());
            S.setObject(10,p.getEmployee().getMiddle());
            S.setObject(11,p.getData());
            S.setObject(12,p.getTime());
            S.setObject(13,p.getComments());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addServices(Services s){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO services ('name', 'price') "+
                    "VALUES(?,?)");
            S.setObject(1,s.getName());
            S.setObject(2,s.getPrice());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addClients(Clients c){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO clients ('surname_clients', 'name_clients','middle_clients','birhdate','email','phone','point') "+
                    "VALUES(?,?,?,?,?,?,?)");
            S.setObject(1,c.getSurname());
            S.setObject(2,c.getName());
            S.setObject(3,c.getMiddle());
            S.setObject(4,c.getBirthdate());
            S.setObject(5,c.getEmail());
            S.setObject(6,c.getPhone());
            S.setObject(7,c.getPoint());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addEmployee(Employee e){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO employee ('surname_employee', 'name_employee','middle_employee','birhdate','post','phone') "+
                    "VALUES(?,?,?,?,?,?)");
            S.setObject(1,e.getSurname());
            S.setObject(2,e.getName());
            S.setObject(3,e.getMiddle());
            S.setObject(4,e.getBirthdate());
            S.setObject(5,e.getPost());
            S.setObject(6,e.getPhone());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addRewards(int p,int p1){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO rewards ('transfersPoint', 'discountPoint') "+
                    "VALUES(?,?)");
            S.setObject(1,p);
            S.setObject(2,p1);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteServices(String[] name){
        try {
            for (int i = 0; i < name.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM services WHERE name=?");
                S.setObject(1,name[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteClients(int[] id){
        try {
            for (int i = 0; i < id.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM clients WHERE id_clients=?");
                S.setObject(1,id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteEmployee(int[] id){
        try {
            for (int i = 0; i < id.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM employee WHERE id_employee=?");
                S.setObject(1,id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deletePerformedWork(int[] id){
        try {
            for (int i = 0; i < id.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM performedWork WHERE id_work=?");
                S.setObject(1,id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteRecord(int[] id){
        try {
            for (int i = 0; i < id.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM record WHERE id_record=?");
                S.setObject(1,id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteServices(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM services");
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteClients(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM clients");
            S.execute();
            S.close();
            Statement statement=connection.createStatement();
            statement.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'clients';");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteEmployee(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM employee");
            S.execute();
            S.close();
            Statement statement=connection.createStatement();
            statement.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'employee';");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deletePerformedWork(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM performedWork");
            S.execute();
            S.close();
            Statement statement=connection.createStatement();
            statement.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'performedWork';");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteRecord(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM record");
            S.execute();
            S.close();
            Statement statement=connection.createStatement();
            statement.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'record';");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void changeServices(String name,Services s){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE services SET 'name'=? ,'price'=?  WHERE name=? ");
            S.setObject(1,s.getName());
            S.setObject(2,s.getPrice());
            S.setObject(3,name);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeRewards(int p,int p1){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE rewards SET 'transfersPoint'=? ,'discountPoint'=?  ");
            S.setObject(1,p);
            S.setObject(2,p1);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeClients(int id,Clients c){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE clients SET 'surname_clients'=?, 'name_clients'=?,'middle_clients'=?,'birhdate'=?,'email'=?,'phone'=?  WHERE id_clients =? ");
            S.setObject(1,c.getSurname());
            S.setObject(2,c.getName());
            S.setObject(3,c.getMiddle());
            S.setObject(4,c.getBirthdate());
            S.setObject(5,c.getEmail());
            S.setObject(6,c.getPhone());
            S.setObject(7,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeClients(int id,float point){
        try {
            //какие значения были до
            PreparedStatement S2 = connection.prepareStatement("SELECT * FROM clients WHERE id_clients =? ");
            S2.setObject(1,id);
            ResultSet resSet = S2.executeQuery();
            Clients p=new Clients(
                    resSet.getInt("id_clients"),
                    resSet.getString("surname_clients") ,
                    resSet.getString("name_clients"),
                    resSet.getString("middle_clients"),
                    resSet.getString("birhdate"),
                    resSet.getString("email"),
                    resSet.getString("phone"),
                    resSet.getFloat("point")
            );
            S2.close();
            //изменение
            PreparedStatement S = connection.prepareStatement("UPDATE clients SET 'surname_clients'=?, 'name_clients'=?,'middle_clients'=?,'birhdate'=?,'email'=?,'phone'=?,'point'=?  WHERE id_clients =? ");
            S.setObject(1,"sname1");
            S.setObject(2,"name1");
            S.setObject(3,"mname1");
            S.setObject(4,"HB1");
            S.setObject(5,"email1");
            S.setObject(6,"phone1");
            S.setObject(7,point);
            S.setObject(8,id);
            S.execute();
            S.close();
            //значение после
            PreparedStatement S1 = connection.prepareStatement("SELECT * FROM clients WHERE id_clients =? ");
            S1.setObject(1,id);
            ResultSet resSet1 = S1.executeQuery();
                Clients p2=new Clients(
                        resSet1.getInt("id_clients"),
                        resSet1.getString("surname_clients") ,
                        resSet1.getString("name_clients"),
                        resSet1.getString("middle_clients"),
                        resSet1.getString("birhdate"),
                        resSet1.getString("email"),
                        resSet1.getString("phone"),
                        resSet1.getFloat("point")
                );
            S1.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeClientsEasy(int id,float point){
        try {
            //какие значения были до
            PreparedStatement S2 = connection.prepareStatement("SELECT * FROM clients WHERE id_clients =? ");
            S2.setObject(1,id);
            ResultSet resSet = S2.executeQuery();
            Clients p=new Clients(
                    resSet.getInt("id_clients"),
                    resSet.getString("surname_clients") ,
                    resSet.getString("name_clients"),
                    resSet.getString("middle_clients"),
                    resSet.getString("birhdate"),
                    resSet.getString("email"),
                    resSet.getString("phone"),
                    resSet.getFloat("point")
            );
            S2.close();

            //изменение
            PreparedStatement S = connection.prepareStatement("UPDATE clients SET 'point'=?  WHERE id_clients =? ");
            S.setObject(1,point);
            S.setObject(2,id);
            S.execute();
            S.close();

            //значение после
            PreparedStatement S1 = connection.prepareStatement("SELECT * FROM clients WHERE id_clients =? ");
            S1.setObject(1,id);
            ResultSet resSet1 = S1.executeQuery();
            Clients p2=new Clients(
                    resSet1.getInt("id_clients"),
                    resSet1.getString("surname_clients") ,
                    resSet1.getString("name_clients"),
                    resSet1.getString("middle_clients"),
                    resSet1.getString("birhdate"),
                    resSet1.getString("email"),
                    resSet1.getString("phone"),
                    resSet1.getFloat("point")
            );
            S1.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void changeEmployee(int id,Employee c){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE employee SET 'surname_employee'=?, 'name_employee'=?,'middle_employee'=?,'birhdate'=?,'post'=?,'phone'=?  WHERE id_employee =? ");
            S.setObject(1,c.getSurname());
            S.setObject(2,c.getName());
            S.setObject(3,c.getMiddle());
            S.setObject(4,c.getBirthdate());
            S.setObject(5,c.getPost());
            S.setObject(6,c.getPhone());
            S.setObject(7,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changePerformedWork(int id,Work p){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE performedWork SET " +
                            "'name'=?," +
                            "'price'=?," +
                            "'point',"+
                            "'income',"+
                            "'id_clients'=?," +
                            "'surname_clients'=?," +
                            "'name_clients'=?," +
                            "'middle_clients'=?," +
                            "'id_employee'=?," +
                            "'surname_employee'=?," +
                            "'name_employee'=?," +
                            "'middle_employee'=?," +
                            "'date'=?," +
                            "'time'=?," +
                            "'comment'=?" +
                    "WHERE 'id_work' =? ");
            S.setObject(1,p.getServices().getName());
            S.setObject(2,p.getServices().getPrice());
            S.setObject(3,p.getPoint());
            S.setObject(4,p.getServices().getPrice()-p.getPoint());
            S.setObject(5,p.getClients().getId());
            S.setObject(6,p.getClients().getSurname());
            S.setObject(7,p.getClients().getName());
            S.setObject(8,p.getClients().getMiddle());
            S.setObject(9,p.getEmployee().getId());
            S.setObject(10,p.getEmployee().getSurname());
            S.setObject(11,p.getEmployee().getName());
            S.setObject(12,p.getEmployee().getMiddle());
            S.setObject(13,p.getData());
            S.setObject(14,p.getTime());
            S.setObject(15,p.getComments());
            S.setObject(16,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeRecord(int id,Record p){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE record SET " +
                    "'name'=?," +
                    "'price'=?," +
                    "'id_clients'=?," +
                    "'surname_clients'=?," +
                    "'name_clients'=?," +
                    "'middle_clients'=?," +
                    "'id_employee'=?," +
                    "'surname_employee'=?," +
                    "'name_employee'=?," +
                    "'middle_employee'=?," +
                    "'date'=?," +
                    "'time'=?," +
                    "'comment'=?" +
                    "WHERE 'id_record' =? ");
            S.setObject(1,p.getServices().getName());
            S.setObject(2,p.getServices().getPrice());
            S.setObject(3,p.getClients().getId());
            S.setObject(4,p.getClients().getSurname());
            S.setObject(5,p.getClients().getName());
            S.setObject(6,p.getClients().getMiddle());
            S.setObject(7,p.getEmployee().getId());
            S.setObject(8,p.getEmployee().getSurname());
            S.setObject(9,p.getEmployee().getName());
            S.setObject(10,p.getEmployee().getMiddle());
            S.setObject(11,p.getData());
            S.setObject(12,p.getTime());
            S.setObject(13,p.getComments());
            S.setObject(14,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List selectServices(){
        try {
            List<Services> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM services ORDER BY name");
            while(resSet.next())
            {
                Services p=new Services(
                        resSet.getString("name") ,
                        resSet.getFloat("price"));
                List.add(p);
            }
            statmt.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static int[] selectRewards(){
        try {
            int[] res=new int[2];
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM rewards");
            while(resSet.next())
            {
                res[0]=resSet.getInt("transfersPoint");
                res[1]=resSet.getInt("discountPoint");
            }
            statmt.close();
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectClients(){
        try {
            List<Clients> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM clients ORDER BY surname_clients,name_clients");
            while(resSet.next())
            {
                Clients p=new Clients(
                        resSet.getInt("id_clients"),
                        resSet.getString("surname_clients") ,
                        resSet.getString("name_clients"),
                        resSet.getString("middle_clients"),
                        resSet.getString("birhdate"),
                        resSet.getString("email"),
                        resSet.getString("phone"),
                        resSet.getInt("point")
                );
                List.add(p);
            }
            statmt.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectEmployee(){
        try {
            List<Employee> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM employee ORDER BY surname_employee,name_employee");
            while(resSet.next())
            {
                Employee p=new Employee(
                        resSet.getInt("id_employee"),
                        resSet.getString("surname_employee") ,
                        resSet.getString("name_employee"),
                        resSet.getString("middle_employee"),
                        resSet.getString("birhdate"),
                        resSet.getString("post"),
                        resSet.getString("phone")
                );
                List.add(p);
            }
            statmt.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectPerformedWork(){
        try {
            List<Work> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM performedWork ORDER BY date,id_employee,time");
            while(resSet.next())
            {
                Work p=new Work(
                    resSet.getInt("id_work"),
                    new Services(
                            resSet.getString("name") ,
                            resSet.getFloat("price")),
                    resSet.getFloat("point"),
                    resSet.getFloat("income") ,
                    new Clients(
                            resSet.getInt("id_clients"),
                            resSet.getString("surname_clients") ,
                            resSet.getString("name_clients"),
                            resSet.getString("middle_clients"),
                            "",
                            "",
                            "",
                               0 ),

                    new Employee(
                            resSet.getInt("id_employee"),
                            resSet.getString("surname_employee") ,
                            resSet.getString("name_employee"),
                            resSet.getString("middle_employee"),
                            "",
                            "",
                            ""),
                    resSet.getString("date"),
                    resSet.getString("time"),
                    resSet.getString("comment")
                );
                List.add(p);
            }
            statmt.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectRecord(){
        try {
            List<Record> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM record  ORDER BY date,id_employee,time");
            while(resSet.next())
            {
                Record p=new Record(
                        resSet.getInt("id_record"),
                        new Services(
                                resSet.getString("name") ,
                                resSet.getFloat("price")),
                        new Clients(
                                resSet.getInt("id_clients"),
                                resSet.getString("surname_clients") ,
                                resSet.getString("name_clients"),
                                resSet.getString("middle_clients"),
                                "",
                                "",
                                "",
                                0),
                        new Employee(
                                resSet.getInt("id_employee"),
                                resSet.getString("surname_employee") ,
                                resSet.getString("name_employee"),
                                resSet.getString("middle_employee"),
                                "",
                                "",
                                ""),
                        resSet.getString("date"),
                        resSet.getString("time"),
                        resSet.getString("comment")
                );
                List.add(p);
            }
            statmt.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectRecordDay(){
        try {
            List<Record> List=new ArrayList();
            Date current = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String day2 = formatter.format(current);
            PreparedStatement S = connection.prepareStatement("SELECT * FROM record WHERE date=? ORDER BY date,id_employee,time");
            S.setObject(1,day2 );
            ResultSet resSet = S.executeQuery();

            while(resSet.next())
            {
                Record p=new Record(
                        resSet.getInt("id_record"),
                        new Services(
                                resSet.getString("name") ,
                                resSet.getFloat("price")),
                        new Clients(
                                resSet.getInt("id_clients"),
                                resSet.getString("surname_clients") ,
                                resSet.getString("name_clients"),
                                resSet.getString("middle_clients"),
                                "",
                                "",
                                "",
                                0),
                        new Employee(
                                resSet.getInt("id_employee"),
                                resSet.getString("surname_employee") ,
                                resSet.getString("name_employee"),
                                resSet.getString("middle_employee"),
                                "",
                                "",
                                ""),
                        resSet.getString("date"),
                        resSet.getString("time"),
                        resSet.getString("comment")
                );
                List.add(p);
            }
            S.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List selectBithday(){
        try {
            List<Clients> List=new ArrayList();

            Date current = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
            String message = formatter.format(current);

            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM clients");
            while(resSet.next())
            {
                if(message.equals(resSet.getString("birhdate"))){
                    Clients p=new Clients(
                        resSet.getInt("id_clients"),
                        resSet.getString("surname_clients") ,
                        resSet.getString("name_clients"),
                        resSet.getString("middle_clients"),
                        resSet.getString("birhdate"),
                        resSet.getString("email"),
                        resSet.getString("phone"),
                        resSet.getInt("point")
                );
                List.add(p);
                }
            }
            statmt.close();

            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectBithdayEmployee(){
        try {
            List<Employee> List=new ArrayList();

            Date current = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");
            String message = formatter.format(current);

            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM employee");
            while(resSet.next())
            {
                if(message.equals(resSet.getString("birhdate"))){
                    Employee p=new Employee(
                            resSet.getInt("id_employee"),
                            resSet.getString("surname_employee") ,
                            resSet.getString("name_employee"),
                            resSet.getString("middle_employee"),
                            resSet.getString("birhdate"),
                            resSet.getString("post"),
                            resSet.getString("phone")
                    );
                    List.add(p);
                }
            }
            statmt.close();

            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List selectNotices(){
            List<Record> List=selectRecordDay();
            Date current = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            String time = formatter.format(current);
            int hh=Integer.parseInt(time.substring(0,2));
            int mm=Integer.parseInt(time.substring(3,5));
            List<Record> ListRes=new ArrayList<>();
            for (Record r:List
                 ) {
                int h=Integer.parseInt(r.getTime().substring(0,2));
                int m=Integer.parseInt(r.getTime().substring(3,5));
                if( (h-hh)<=1 & (m-mm)<=60 ){
                    ListRes.add(r);
                }
            }
            return ListRes;
    }
}
