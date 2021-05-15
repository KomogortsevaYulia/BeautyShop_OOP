package com.company.Model;

import com.company.Essence.Services;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelServices extends AbstractTableModel {

    private List<Services> data;

    public TableModelServices(){
        DBWorker.initDB();
        data=DBWorker.selectServices();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectServices();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Services c= data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getName();
            case 1:
                return c.getPrice();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Название";
            case 1:
                return "Стоимость";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(Services p){
        DBWorker.initDB();
        DBWorker.addServices(p);
        DBWorker.closeDB();
        update();
    }

    public void changeRow(String name,Services s){
        DBWorker.initDB();
        DBWorker.changeServices(name,s);
        DBWorker.closeDB();
        update();
    }

    public void deleteRow(String[] name){
        DBWorker.initDB();
        DBWorker.deleteServices(name);
        DBWorker.closeDB();
        update();
    }
}
