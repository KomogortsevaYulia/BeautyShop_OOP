package com.company.Model;

import com.company.Essence.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModelEmployee extends AbstractTableModel {
    private List<Employee> data;

    public TableModelEmployee(){
        DBWorker.initDB();
        data=DBWorker.selectEmployee();
        DBWorker.closeDB();
    }
    public void update(){
        DBWorker.initDB();
        data=DBWorker.selectEmployee();
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
        Employee employee = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getSurname();
            case 2:
                return employee.getName();
            case 3:
                return employee.getMiddle();
            case 4:
                return employee.getBirthdate();
            case 5:
                return employee.getPost();
            case 6:
                return employee.getPhone();
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
                return "Дата рождения";
            case 5:
                return "Должность";
            case 6:
                return "Телефон";
        }
        return null;
    }
    public void addRow(Employee p){
        DBWorker.initDB();
        DBWorker.addEmployee(p);
        DBWorker.closeDB();
        update();
    }
    public void deleteRow(int[] id){
        DBWorker.initDB();
        DBWorker.deleteEmployee(id);
        DBWorker.closeDB();
        update();
    }
    public void changeRow(int id,Employee e){
        DBWorker.initDB();
        DBWorker.changeEmployee(id,e);
        DBWorker.closeDB();
        update();
    }
}
