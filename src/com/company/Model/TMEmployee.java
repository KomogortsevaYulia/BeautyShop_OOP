package com.company.Model;

import javax.swing.table.AbstractTableModel;
import java.util.EventListener;
import java.util.List;

public class TMEmployee extends AbstractTableModel {

    private List<Employee> data;

    public TMEmployee(){
        DBWorker.initDB();
        data=DBWorker.getEmployee();
        DBWorker.closeDB();
    }

    public void update(){
        DBWorker.initDB();
        data=DBWorker.getEmployee();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee c=data.get(rowIndex);
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
                return c.getPost();
            case 6:
                return c.getPhone();
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
                return String.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "День рождения";
            case 5:
                return "Должность";
            case 6:
                return "Телефон";
        }
        return null;
    }

    @Override
    public <T extends EventListener> T[] getListeners(Class<T> listenerType) {
        return super.getListeners(listenerType);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(Employee s){
        DBWorker.initDB();
        DBWorker.addEmployee(s);
        DBWorker.closeDB();
        update();
    }
    public void changeRow(int id,Employee s){
        DBWorker.initDB();
        DBWorker.changeEmployee(id,s);
        DBWorker.closeDB();
        update();
    }
    public void deleteRow(int[] id){
        DBWorker.initDB();
        DBWorker.deleteEmployee(id);
        DBWorker.closeDB();
        update();
    }

    public List<Employee> getData() {
        update();
        return data;
    }
}
