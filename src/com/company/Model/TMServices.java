package com.company.Model;

import javax.swing.table.AbstractTableModel;
import java.util.EventListener;
import java.util.List;

public class TMServices extends AbstractTableModel {
    private List<Services> data;

    public TMServices(){
        DBWorker.initDB();
        data=DBWorker.getServices();
        DBWorker.closeDB();
    }

    public void update(){
        DBWorker.initDB();
        data=DBWorker.getServices();
        DBWorker.closeDB();
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Services c=data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getName();
            case 2:
                return c.getPrice();
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
                return Float.class;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Название";
            case 2:
                return "Стоимость";
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(Services s){
        DBWorker.initDB();
        DBWorker.addServices(s);
        DBWorker.closeDB();
        update();
    }
    public void changeRow(int id,Services s){
        DBWorker.initDB();
        DBWorker.changeServices(id,s);
        DBWorker.closeDB();
        update();
    }
    public void deleteRow(int[] id){
        DBWorker.initDB();
        DBWorker.deleteServices(id);
        DBWorker.closeDB();
        update();
    }

    public List<Services> getData() {
        update();
        return data;
    }
}
