package test;

import main.essence.Services;
import main.data.tableModel.TableModelServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LogTest {

    @Test
    void addLogForAddServices() {
        Services s=new Services("wwww",1000);
        TableModelServices TMServices=new TableModelServices();
        TMServices.addRow(s);
        TMServices.update();

        String[] id=new String[]{s.getName()};
        TMServices.deleteRow(id);
        TMServices.update();


        List<String> lis=new ArrayList<String>();
        try {
            File file = new File("src/main/data/log/Log.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            lis.add(line);
            while (line != null) {
                lis.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exist=false;
        for (String e:lis
             ) {
            if(e.contains("Таблица \"Услуги\" " + " Добавили услугу: Название:" + s.getName() +
                    " Стоимость: " + s.getPrice())){
                exist=true;
            }
        }
        Assertions.assertTrue(exist);
    }
    @Test
    void addLogForDeleteServices() {
        Services s=new Services("wwww",1000);
        TableModelServices TMServices=new TableModelServices();
        TMServices.addRow(s);
        TMServices.update();

        String[] id=new String[]{s.getName()};
        TMServices.deleteRow(id);
        TMServices.update();


        List<String> lis=new ArrayList<String>();
        try {
            File file = new File("src/main/data/log/Log.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            lis.add(line);
            while (line != null) {
                lis.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exist=false;
        for (String e:lis
        ) {
            if(e.contains("Таблица \"Услуги\" " + " Удалили услугу: Название:" + s.getName() +
                    " Стоимость: " + s.getPrice())){
                exist=true;
            }
        }
        Assertions.assertTrue(exist);
    }
    @Test
    void addLogForChangeServices() {
        Services s=new Services("wwww",1000);
        Services s2=new Services("qqq",500);
        TableModelServices TMServices=new TableModelServices();
        TMServices.addRow(s);
        TMServices.update();

        TMServices.changeRow(s.getName(),s2);
        TMServices.update();
        String[] id=new String[]{s2.getName()};
        TMServices.deleteRow(id);
        TMServices.update();


        List<String> lis=new ArrayList<String>();
        try {
            File file = new File("src/main/data/log/Log.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            lis.add(line);
            while (line != null) {
                lis.add(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exist=false;
        for (String e:lis
        ) {
            if(e.contains("Таблица \"Услуги\" " + " Изменили данные услуги: Название:" + s.getName() +
                    " Стоимость: " + s.getPrice()+
                    " На следующие данные: Название: "+s2.getName()+" Стоимость: "+s2.getPrice())){
                exist=true;
            }
        }
        Assertions.assertTrue(exist);
    }
}