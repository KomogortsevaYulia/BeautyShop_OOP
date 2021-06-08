package com.company.Model;

import com.company.Essence.Clients;
import com.company.Essence.Work;
import com.company.View.FramePerformedWork;
import com.company.View.FrameRecord;
import com.company.View.FrameRecordDay;
import com.company.View.MainFrame;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelClients extends AbstractTableModel {
    public List<Clients> data;

    public TableModelClients(){
        DBWorker.initDB();
        data=DBWorker.selectClients();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectClients();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clients c= data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getSurname();
            case 2:
                return c.getName();
            case 3:
                return c.getMiddle();
            case 4:
                return c.getBirthdate();
            case 5:
                return c.getEmail();
            case 6:
                return c.getPhone();
            case 7:
                return c.getPoint();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return Long.class;
            case 7:
                return Integer.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Дата рождения";
            case 5:
                return "Почта";
            case 6:
                return "Телефон";
            case 7:
                return "Баллы";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    public List<Clients> getList(){
        return data;
    }
    public Clients getRow(int id){
        for (Clients c:data
        ) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public String selectRowPhone(int id){
        for (Clients c:data
             ) {
            if(c.getId() == id) {
                return c.getPhone();
            }
        }
        return null;
    }
    public boolean addRow(Clients p){
        boolean res=false;
        for (Clients c:data
             ) {
            if (c==p) {res=false;}
            else{
                res= true;
            }
        }
        if(res) {
            DBWorker.initDB();
            DBWorker.addClients(p);
            DBWorker.closeDB();
            UpdateTM.updateTM();
            for (Clients c : data
            ) {
                if (c.getSurname().equals(p.getSurname()) & c.getName().equals(p.getName()) && c.getMiddle().equals(p.getMiddle())) {
                    Сancellation.addLog("Таблица \"Клиенты\" " + " Добавили клиента: ID:" + c.getId() +
                            " ФИО: " + c.getSurname() + " " + c.getName() + " " + c.getMiddle() +
                            " Телефон: " + c.getPhone() + " День рождения: " + c.getBirthdate() + " Почта: " + c.getEmail()
                            + " Баллы: " + c.getPoint());
                }
            }
        }
        return res;
    }
    public boolean changeRow(int id,Clients p){
        boolean res=false;
        for (Clients c:data
        ) {
            if (c==p) {res=false;}
            else{
                res= true;
            }
        }
        if(res) {
            Clients clients = null;
            for (Clients c : data
            ) {
                if (id == c.getId()) {
                    clients = c;
                }
            }
            DBWorker.initDB();
            DBWorker.changeClients(id, p);
            DBWorker.closeDB();
            UpdateTM.updateTM();
            for (Clients c : data
            ) {
                if (c.getId() == id) {
                    Сancellation.addLog("Таблица \"Клиенты\" " + " Изменили данные клиента: ID:" + c.getId() +
                            " ФИО: " + clients.getSurname() + " " + clients.getName() + " " + clients.getMiddle() +
                            " Телефон: " + clients.getPhone() + " День рождения: " + clients.getBirthdate() + " Почта: " + clients.getEmail()
                            + " Баллы: " + clients.getPoint() +
                            " На следующие данные:" + " ФИО: " + c.getSurname() + " " + c.getName() + " " + c.getMiddle() +
                            " Телефон: " + c.getPhone() + " День рождения: " + c.getBirthdate() + " Почта: " + c.getEmail()
                            + " Баллы: " + c.getPoint());
                }
            }
        }
        return res;
    }
    public void deleteRow(int[] id){
        for (int i = 0; i < id.length; i++) {
            for (Clients c:data
            ) {
                if(id[i]==c.getId()){
                    Сancellation.addLog("Таблица \"Клиенты\" "+" Удалили данные клиента: ID:"+c.getId()+
                            " ФИО: "+c.getSurname()+" "+c.getName()+" "+c.getMiddle()+
                            " Телефон: "+c.getPhone()+" День рождения: "+c.getBirthdate()+ " Почта: "+c.getEmail()
                            +" Баллы: "+c.getPoint());
                }
            }
        }
        DBWorker.initDB();
        DBWorker.deleteClients(id);
        DBWorker.closeDB();
        UpdateTM.updateTM();
    }
}
