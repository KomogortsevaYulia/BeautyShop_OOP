package com.company.Model;

import com.company.Essence.Clients;
import com.company.Essence.Employee;
import com.company.Essence.Services;

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
            statement.execute("CREATE TABLE if not exists 'services' " +
                    "(" +
                    " 'id_services' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'name' text NOT NULL," +
                    " 'price' float NOT NULL);");
            statement.execute("CREATE TABLE if not exists 'clients' " +
                    "(" +
                    " 'id_clients' INTEGER PRIMARY  KEY AUTOINCREMENT," +
                    " 'surname_clients' text," +
                    " 'name_clients' text NOT NULL," +
                    " 'middle_clients' text," +
                    " 'birhdate' text," +
                    " 'email' text," +
                    " 'phone' text NOT NULL);");
            statement.execute("CREATE TABLE if not exists 'employee' " +
                    "(" +
                    " 'id_employee' INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " 'surname_employee' text," +
                    " 'name_employee' text NOT NULL," +
                    " 'middle_employee' text," +
                    " 'birhdate' text," +
                    " 'post' text," +
                    " 'phone' text NOT NULL);");
            statement.close();
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
            PreparedStatement S = connection.prepareStatement("INSERT INTO clients ('surname_clients', 'name_clients','middle_clients','birhdate','email','phone') "+
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

    public static void deleteServices(int[] id){
        try {
            for (int i = 0; i < id.length; i++) {
                PreparedStatement S = connection.prepareStatement("DELETE FROM services WHERE id_services=?");
                S.setObject(1,id[i]);
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

    public static void deleteServices(){
        try {
            PreparedStatement S = connection.prepareStatement("DELETE FROM services");
            S.execute();
            S.close();
            Statement statement=connection.createStatement();
            statement.execute("UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'services';");
            statement.close();
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

    public static void changeServices(int id,Services s){
        try {
            PreparedStatement S = connection.prepareStatement("UPDATE services SET 'name'=? ,'price'=?  WHERE id_services =? ");
            S.setObject(1,s.getName());
            S.setObject(2,s.getPrice());
            S.setObject(3,id);
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

    public static List selectServices(){
        try {
            List<Services> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM services");
            while(resSet.next())
            {
                Services p=new Services(
                        resSet.getInt("id_services"),
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
    public static List selectClients(){
        try {
            List<Clients> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM clients");
            while(resSet.next())
            {
                Clients p=new Clients(
                        resSet.getInt("id_clients"),
                        resSet.getString("surname_clients") ,
                        resSet.getString("name_clients"),
                        resSet.getString("middle_clients"),
                        resSet.getString("birhdate"),
                        resSet.getString("email"),
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
    public static List selectEmployee(){
        try {
            List<Employee> List=new ArrayList();
            Statement statmt = connection.createStatement();
            ResultSet resSet = statmt.executeQuery("SELECT * FROM employee");
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
}
