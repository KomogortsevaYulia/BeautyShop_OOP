package com.company.Model;

import java.sql.*;
import java.util.ArrayList;
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
            statement.execute("CREATE TABLE if not exists services (\n" +
                    "    id_services   INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    name_services TEXT    NOT NULL,\n" +
                    "    price         FLOAT   NOT NULL\n" +
                    ");");
            statement.close();
            statement.execute("CREATE TABLE if not exists clients (\n" +
                    "    id_clients      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    surname_clients TEXT,\n" +
                    "    name_clients    TEXT    NOT NULL,\n" +
                    "    middle_clients  TEXT,\n" +
                    "    birthdate       TEXT,\n" +
                    "    email           TEXT,\n" +
                    "    phone           INTEGER     NOT NULL\n" +
                    ");");
            statement.execute("CREATE TABLE if not exists employee (\n" +
                    "    id_employee      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    surname_employee TEXT    NOT NULL,\n" +
                    "    name_employee    TEXT    NOT NULL,\n" +
                    "    middle_employee  TEXT    NOT NULL,\n" +
                    "    birthdate        TEXT,\n" +
                    "    post             TEXT    NOT NULL,\n" +
                    "    phone            INTEGER     NOT NULL\n" +
                    ");");
            /*statement.execute("CREATE TABLE if not exists registry (\n" +
                    "    id_registry INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    id_clients  INT     NOT NULL,\n" +
                    "    id_services INT     NOT NULL,\n" +
                    "    id_employee INT     NOT NULL,\n" +
                    "    date        TEXT    NOT NULL,\n" +
                    "    time        TEXT    NOT NULL,\n" +
                    "    comment     TEXT\n" +
                    ");" );*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void changeServices(int id,Services s){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE services SET 'name_services'=? ,'price'=?  WHERE id_services=? ");
            S.setObject(1,s.getName());
            S.setObject(2,s.getPrice());
            S.setObject(3,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeClients(int id,Clients s){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE clients SET 'surname_clients'=? ,'name_clients'=?," +
                    "'middle_clients'=? ,'birthdate'=?," +
                    "'email'=? ,'phone'=?" +
                    "  WHERE id_clients=? ");
            S.setObject(1,s.getSurname());
            S.setObject(2,s.getName());
            S.setObject(3,s.getMiddle());
            S.setObject(4,s.getBirthdate());
            S.setObject(5,s.getEmail());
            S.setObject(6,s.getPhone());
            S.setObject(7,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void changeEmployee(int id,Employee s){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE employee SET 'surname_employee'=? ,'name_employee'=?," +
                    "'middle_employee'=? ,'birthdate'=?," +
                    "'post'=? ,'phone'=?" +
                    "  WHERE id_employee=? ");
            S.setObject(1,s.getSurname());
            S.setObject(2,s.getName());
            S.setObject(3,s.getMiddle());
            S.setObject(4,s.getBirthdate());
            S.setObject(5,s.getPost());
            S.setObject(6,s.getPhone());
            S.setObject(7,id);
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addServices(Services p){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO services ('name_services', 'price') "+
                    "VALUES(?,?)");
            S.setObject(1,p.getName());
            S.setObject(2,p.getPrice());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addClients(Clients c){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO clients ('surname_clients','name_clients','middle_clients','birthdate','email','phone') "+
                    "VALUES(?,?,?,?,?,?)");
            S.setObject(1,c.getSurname());
            S.setObject(2,c.getName());
            S.setObject(3,c.getMiddle());
            S.setObject(4,c.getBirthdate());
            S.setObject(5,c.getEmail());
            S.setObject(6,c.getPhone());
            S.execute();
            S.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addEmployee(Employee e){
        try {
            PreparedStatement S = connection.prepareStatement("INSERT INTO employee ('surname_employee','name_employee','middle_employee','birthdate','post','phone') "+
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

    public static List getServices(){
        try {
            List<Services> List=new ArrayList();
            Statement S = connection.createStatement();
            ResultSet resSet = S.executeQuery("SELECT * FROM services");
            while(resSet.next())
            {
                Services p=new Services(
                        resSet.getInt("id_services"),
                        resSet.getString("name_services") ,
                        resSet.getInt("price")
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
    public static List getClients(){
        try {
            List<Clients> List=new ArrayList();
            Statement S = connection.createStatement();
            ResultSet resSet = S.executeQuery("SELECT * FROM clients");
            while(resSet.next())
            {
                Clients c=new Clients(
                        resSet.getInt("id_clients"),
                        resSet.getString("surname_clients"),
                        resSet.getString("name_clients"),
                        resSet.getString("middle_clients"),
                        resSet.getString("birthdate"),
                        resSet.getString("email"),
                        resSet.getLong("phone")
                );
                List.add(c);
            }
            S.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static List getEmployee(){
        try {
            List<Employee> List=new ArrayList();
            Statement S = connection.createStatement();
            ResultSet resSet = S.executeQuery("SELECT * FROM employee");
            while(resSet.next())
            {
                Employee c=new Employee(
                        resSet.getInt("id_employee"),
                        resSet.getString("surname_employee"),
                        resSet.getString("name_employee"),
                        resSet.getString("middle_employee"),
                        resSet.getString("birthdate"),
                        resSet.getString("post"),
                        resSet.getLong("phone")
                );
                List.add(c);
            }
            S.close();
            return List;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void deleteServices(){
        try {
            Statement S2 = connection.createStatement();
            S2.execute("DELETE FROM services");
            S2.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'services';");
            S2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteClients(){
        try {
            Statement S2 = connection.createStatement();
            S2.execute("DELETE FROM clients");
            S2.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'clients';");
            S2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteEmployee(){
        try {
            Statement S2 = connection.createStatement();
            S2.execute("DELETE FROM employee");
            S2.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'employee';");
            S2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteServices(int[] id){
        try {
            for (int i = 0; i <id.length ; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM services WHERE id_services=?");
                S.setObject(1, id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void deleteClients(int[] id){
        try {
            for (int i = 0; i <id.length ; i++) {
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
                S.setObject(1, id[i]);
                S.execute();
                S.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
